package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.dto.answer.AnswerDTO;
import mz.org.csaude.mentoring.dto.mentorship.MentorshipDTO;
import mz.org.csaude.mentoring.dto.session.SessionDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.mentorship.Door;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionRestService extends BaseRestService {
    public SessionRestService(Application application) {
        super(application);
    }
    public void restGetSessions(RestResponseListener<Session> listener) {
        List<Ronda> rondas = null;
        try {
            rondas = getApplication().getRondaService().getAll();
            List<String> rondasUuids = new ArrayList<>();
            for (Ronda ronda: rondas) {
                rondasUuids.add(ronda.getUuid());
             }

        Call<List<SessionDTO>> mentorshipsCall = syncDataService.getAllOfRondas(rondasUuids);

        mentorshipsCall.enqueue(new Callback<List<SessionDTO>>() {
            @Override
            public void onResponse(Call<List<SessionDTO>> call, Response<List<SessionDTO>> response) {
                List<SessionDTO> data = response.body();
                if (Utilities.listHasElements(data)) {
                    try {
                        List<Session> sessions = new ArrayList<>();
                        for (SessionDTO sessionDTO: data) {
                            Session session = new Session(sessionDTO);
                            session.setSyncStatus(SyncSatus.SENT);
                            Ronda ronda = (Ronda) Utilities.extractElementByUuid(getApplication().getRondaService().getAll(), sessionDTO.getRonda().getUuid());
                            session.setRonda(ronda);
                            Form form = getApplication().getFormService().getByuuid(sessionDTO.getForm().getUuid());
                            session.setForm(form);
                            Tutored mentee = (Tutored) Utilities.extractElementByUuid(getApplication().getTutoredService().getAll(), sessionDTO.getMentee().getUuid());
                            session.setTutored(mentee);
                            session.setStatus(getApplication().getSessionStatusService().getByuuid(sessionDTO.getSessionStatus().getUuid()));
                            getApplication().getSessionService().saveOrUpdate(session);
                            List<MentorshipDTO> mentorships = sessionDTO.getMentorships();
                            updateMentorships(mentorships, form, mentee, session);
                        }
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, sessions);
                    } catch (SQLException e) {
                        Log.e("SessionRestService", e.getMessage());
                    }
                } else {
                    listener.doOnResponse(BaseRestService.REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<SessionDTO>> call, Throwable t) {
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
        } catch (SQLException e) {
            Log.e("SessionRestService", e.getMessage());
        }
    }

    private void updateMentorships(List<MentorshipDTO> mentorships, Form form, Tutored mentee, Session session) throws SQLException {
        for (MentorshipDTO mentorshipDTO : mentorships) {
            Mentorship mentorship = new Mentorship();
            mentorship.setUuid(mentorshipDTO.getUuid());
            mentorship.setCreatedAt(mentorshipDTO.getCreatedAt());
            mentorship.setUpdatedAt(mentorshipDTO.getUpdatedAt());
            if(mentorshipDTO.getLifeCycleStatus()!=null) mentorship.setLifeCycleStatus(mentorshipDTO.getLifeCycleStatus());
            mentorship.setStartDate(mentorshipDTO.getStartDate());
            mentorship.setEndDate(mentorshipDTO.getEndDate());
            mentorship.setIterationNumber(mentorshipDTO.getIterationNumber());
            mentorship.setDemonstration(mentorshipDTO.isDemonstration());
            mentorship.setDemonstrationDetails(mentorshipDTO.getDemonstrationDetails());
            mentorship.setPerformedDate(mentorshipDTO.getPerformedDate());
            mentorship.setTutored(mentee);
            Tutor mentor = (Tutor) Utilities.extractElementByUuid(getApplication().getTutorService().getAll(), mentorshipDTO.getMentor().getUuid());
            mentorship.setTutor(mentor);
            mentorship.setForm(form);
            mentorship.setEvaluationType(getApplication().getEvaluationTypeService().getByuuid(mentorshipDTO.getEvaluationType().getUuid()));
            Door door = (Door) Utilities.extractElementByUuid(getApplication().getDoorService().getAll(), mentorshipDTO.getDoor().getUuid());
            mentorship.setDoor(door);
            Cabinet cabinet = (Cabinet) Utilities.extractElementByUuid(getApplication().getCabinetService().getAll(), mentorshipDTO.getCabinet().getUuid());
            mentorship.setCabinet(cabinet);
            mentorship.setSyncStatus(SyncSatus.SENT);
            mentorship.setSession(session);
            getApplication().getMentorshipService().saveOrUpdate(mentorship);
            List<AnswerDTO> answers = mentorshipDTO.getAnswers();
            updateAnswers(answers, mentorship, form);
        }
    }

    private void updateAnswers(List<AnswerDTO> answers, Mentorship mentorship, Form form) throws SQLException {
        for (AnswerDTO answerDTO: answers) {
            Answer answer = new Answer();
            answer.setMentorship(mentorship);
            answer.setQuestion(getApplication().getQuestionService().getByuuid(answerDTO.getQuestion().getUuid()));
            answer.setForm(form);
            answer.setSyncStatus(SyncSatus.SENT);
            answer.setUuid(answerDTO.getUuid());
            answer.setCreatedAt(answerDTO.getCreatedAt());
            answer.setUpdatedAt(answerDTO.getUpdatedAt());
            if(answerDTO.getLifeCycleStatus()!=null) answer.setLifeCycleStatus(answerDTO.getLifeCycleStatus());
            answer.setValue(answerDTO.getValue());
            getApplication().getAnswerService().saveOrUpdate(answer);
        }
    }
}
