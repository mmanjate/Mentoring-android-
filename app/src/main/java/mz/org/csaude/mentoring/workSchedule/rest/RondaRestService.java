package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.ronda.RondaDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.service.ronda.RondaServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RondaRestService extends BaseRestService {

    public RondaRestService(Application application) {
        super(application);
    }

    public void restGetRondas(RestResponseListener<Ronda> listener){
        long mentorId = getApplication().getCurrMentor().getId();
        Call<List<RondaDTO>> rondasCall = syncDataService.getAllRondasOfMentor(mentorId);

        rondasCall.enqueue(new Callback<List<RondaDTO>>() {
            @Override
            public void onResponse(Call<List<RondaDTO>> call, Response<List<RondaDTO>> response) {
                List<RondaDTO> data = response.body();
                if (Utilities.listHasElements(data)) {
                    try {
                        RondaService rondaService = new RondaServiceImpl(LoadMetadataServiceImpl.APP);
                        List<Ronda> rondas = new ArrayList<>();

                        for (RondaDTO rondaDTO: data) {
                            rondaDTO.setSyncSatus(SyncSatus.SENT);
                            Ronda ronda = rondaDTO.getRonda();
                            ronda.setSyncStatus(SyncSatus.SENT);
                            rondas.add(ronda);
                        }
                        Toast.makeText(APP.getApplicationContext(), "Carregando as Rondas.", Toast.LENGTH_SHORT).show();
                        rondaService.saveOrUpdateRondas(data);

                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, rondas);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "RONDAS CARREGADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(BaseRestService.REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<RondaDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as Rondas. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
    }

    public void restPostRondas(RestResponseListener<Ronda> listener){

        List<Ronda> rondas = null;
        try {
            rondas = getApplication().getRondaService().getAllNotSynced();
            if (Utilities.listHasElements(rondas)) {
                Call<List<RondaDTO>> rondaCall = syncDataService.postRondas(Utilities.parse(rondas, RondaDTO.class));
                rondaCall.enqueue(new Callback<List<RondaDTO>>() {
                    @Override
                    public void onResponse(Call<List<RondaDTO>> call, Response<List<RondaDTO>> response) {
                        List<RondaDTO> data = response.body();
                        if (response.code() == 201) {
                            try {
                                List<Ronda> rondaList = getApplication().getRondaService().getAllNotSynced();
                                for (Ronda ronda : rondaList) {
                                    ronda.setSyncStatus(SyncSatus.SENT);
                                    getApplication().getRondaService().update(ronda);
                                }

                                listener.doOnResponse(BaseRestService.REQUEST_SUCESS, rondaList);
                            } catch (SQLException  e) {
                                throw new RuntimeException(e);
                            }
                        } else listener.doOnRestErrorResponse(response.message());
                    }

                    @Override
                    public void onFailure(Call<List<RondaDTO>> call, Throwable t) {
                        Log.i("METADATA LOAD --", t.getMessage(), t);
                    }
                });
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
