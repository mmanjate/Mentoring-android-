package mz.org.csaude.mentoring.service.session;

import android.app.Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.mentorship.MentorshipDAO;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.dao.session.SessionRecommendedResourceDAO;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;
import mz.org.csaude.mentoring.model.session.SessionSummary;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.DateUtilities;

public class SessionServiceImpl extends BaseServiceImpl<Session> implements SessionService{

    SessionDAO sessionDAO;
    MentorshipDAO mentorshipDAO;

    SessionRecommendedResourceDAO sessionRecommendedResourceDAO;

    public SessionServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.sessionDAO = getDataBaseHelper().getSessionDAO();
        this.mentorshipDAO = getDataBaseHelper().getMentorshipDAO();
        this.sessionRecommendedResourceDAO = getDataBaseHelper().getSessionRecommendedResourceDAO();
    }

    @Override
    public Session save(Session record) throws SQLException {
        this.sessionDAO.create(record);
        return record;
    }

    @Override
    public Session update(Session record) throws SQLException {
        this.sessionDAO.update(record);
        return record;
    }

    @Override
    public int delete(Session record) throws SQLException {
        return this.sessionDAO.delete(record);
    }

    @Override
    public List<Session> getAll() throws SQLException {
        return this.sessionDAO.queryForAll();
    }

    @Override
    public Session getById(int id) throws SQLException {
        return this.sessionDAO.queryForId(id);
    }

    @Override
    public List<Session> getAllOfRondaAndMentee(Ronda currRonda, Tutored selectedMentee, long offset, long limit) throws SQLException {
        List<Session> sessions = this.sessionDAO.queryForAllOfRondaAndMentee(currRonda, selectedMentee, getApplication());
        for (Session session : sessions) {
            session.setMentorships(this.mentorshipDAO.getAllOfSession(session));
        }
        return this.sessionDAO.queryForAllOfRondaAndMentee(currRonda, selectedMentee, getApplication());
    }

    @Override
    public List<Session> getAllOfRonda(Ronda ronda) throws SQLException {
        return this.sessionDAO.queryForAllOfRonda(ronda);
    }

    @Override
    public List<SessionSummary> generateSessionSummary(Session session) {
        List<SessionSummary> summaries = new ArrayList<>();

        try {
            session.setMentorships(getApplication().getMentorshipService().getAllOfSession(session));
            for (Mentorship mentorship : session.getMentorships()) {
                if (mentorship.isPatientEvaluation()) {
                    mentorship.setAnswers(getApplication().getAnswerService().getAllOfMentorship(mentorship));
                    for (Answer answer : mentorship.getAnswers()) {
                        String cat = answer.getQuestion().getQuestionsCategory().getCategory();
                        if (categoryAlreadyExists(cat, summaries)){
                            doCountInCategory(cat, summaries, answer);
                        } else {
                            summaries.add(initSessionSummary(answer));
                        }
                    }
                    break;
                }
            }
            return summaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveRecommendedResources(Session session, List<SessionRecommendedResource> recommendedResources) throws SQLException {
        for (SessionRecommendedResource recommendedResource : recommendedResources) {
            this.sessionRecommendedResourceDAO.create(recommendedResource);
        }
    }

    private void doCountInCategory(String cat, List<SessionSummary> summaries, Answer answer) {
        for (SessionSummary sessionSummary : summaries) {
            if (sessionSummary.getTitle().equals(cat)) {
                if (answer.getValue().equals("SIM")) {
                    sessionSummary.setSimCount(sessionSummary.getSimCount() + 1);
                } else if (answer.getValue().equals("NAO")) {
                    sessionSummary.setNaoCount(sessionSummary.getNaoCount() + 1);
                }
            }
        }
    }

    private boolean categoryAlreadyExists(String cat, List<SessionSummary> summaries) {
        for (SessionSummary sessionSummary : summaries) {
            if (sessionSummary.getTitle().equals(cat)) {
                return true;
            }
        }
        return false;
    }

    private SessionSummary initSessionSummary(Answer answer) {
        SessionSummary sessionSummary = new SessionSummary();
        sessionSummary.setTitle(answer.getQuestion().getQuestionsCategory().getCategory());

        if (answer.getValue().equals("SIM")) {
            sessionSummary.setSimCount(sessionSummary.getSimCount() + 1);
        } else if (answer.getValue().equals("NAO")) {
            sessionSummary.setNaoCount(sessionSummary.getNaoCount() + 1);
        }
        return sessionSummary;
    }

    @Override
    public List<Session> getAllOfRondaPending(Ronda ronda) throws SQLException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Date startDate = cal.getTime();
        return this.sessionDAO.getAllOfRondaPending(ronda, startDate);
    }
}
