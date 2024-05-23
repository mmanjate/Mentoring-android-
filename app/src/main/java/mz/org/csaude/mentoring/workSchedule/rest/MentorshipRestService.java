package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.mentorship.MentorshipDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MentorshipRestService extends BaseRestService {


    public MentorshipRestService(Application application) {
        super(application);
    }

    public void restGetMentorships(RestResponseListener<Mentorship> listener){
        long partnerId = getApplication().getCurrMentor().getEmployee().getPartner().getId();
        Call<List<MentorshipDTO>> mentorshipsCall = syncDataService.getAllMentorshipSessionsOfMentor(partnerId);

        mentorshipsCall.enqueue(new Callback<List<MentorshipDTO>>() {
            @Override
            public void onResponse(Call<List<MentorshipDTO>> call, Response<List<MentorshipDTO>> response) {
                List<MentorshipDTO> data = response.body();
                if (Utilities.listHasElements(data)) {
                    try {
                        MentorshipService mentorshipService = new MentorshipServiceImpl(LoadMetadataServiceImpl.APP);
                        List<Mentorship> mentorships = new ArrayList<>();
                        for (MentorshipDTO mentorshipDTO: data) {
                            mentorshipDTO.setSyncSatus(SyncSatus.SENT);
                            Mentorship mentorship = mentorshipDTO.getMentorship();
                            mentorship.setSyncStatus(SyncSatus.SENT);
                            mentorships.add(mentorship);
                        }
                        Toast.makeText(APP.getApplicationContext(), "Carregando as Sessões de Mentoria.", Toast.LENGTH_SHORT).show();
                        mentorshipService.saveOrUpdateMentorships(data);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, mentorships);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "SESSÕES DE MENTORIA CARREGADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(BaseRestService.REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<MentorshipDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as Sessões de Mentoria. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
    }


    public void restPostMentorships(RestResponseListener<Mentorship> listener){

        List<Mentorship> mentorships = null;
        try {
            mentorships = getApplication().getMentorshipService().getAllNotSynced();
        if (Utilities.listHasElements(mentorships)) {
            Call<List<MentorshipDTO>> mentorshipsCall = syncDataService.postMenthorships(Utilities.parse(mentorships, MentorshipDTO.class));
            mentorshipsCall.enqueue(new Callback<List<MentorshipDTO>>() {
                @Override
                public void onResponse(Call<List<MentorshipDTO>> call, Response<List<MentorshipDTO>> response) {
                    List<MentorshipDTO> data = response.body();
                    if (response.code() == 201) {
                        try {
                            List<Mentorship> mentorshipList = getApplication().getMentorshipService().getAllNotSynced();
                            for (Mentorship mentorship : mentorshipList) {
                                mentorship.setSyncStatus(SyncSatus.SENT);
                                getApplication().getMentorshipService().update(mentorship);
                            }

                            listener.doOnResponse(BaseRestService.REQUEST_SUCESS, mentorshipList);
                        } catch (SQLException  e) {
                            throw new RuntimeException(e);
                        }
                    } else listener.doOnRestErrorResponse(response.message());
                }

                @Override
                public void onFailure(Call<List<MentorshipDTO>> call, Throwable t) {
                    Log.i("METADATA LOAD --", t.getMessage(), t);
                }
            });
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
