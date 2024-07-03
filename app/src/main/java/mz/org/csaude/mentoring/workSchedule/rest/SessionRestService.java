package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.dto.mentorship.MentorshipDTO;
import mz.org.csaude.mentoring.dto.session.SessionDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
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
                        List<Session> sessions = Utilities.parseList(data, Session.class);
                        for (Session session: sessions) {
                            session.setSyncStatus(SyncSatus.SENT);
                            session.setRonda(getApplication().getRondaService().getByuuid(session.getRonda().getUuid()));
                            session.setForm(getApplication().getFormService().getByuuid(session.getForm().getUuid()));
                            session.setTutored(getApplication().getTutoredService().getByuuid(session.getTutored().getUuid()));
                            session.setStatus(getApplication().getSessionStatusService().getByuuid(session.getStatus().getUuid()));
                            getApplication().getSessionService().save(session);
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
}
