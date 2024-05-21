package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.location.RondaTypeDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaTypeService;
import mz.org.csaude.mentoring.service.ronda.RondaTypeServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RondaTypeRestService extends BaseRestService {
    public RondaTypeRestService(Application application) {
        super(application);
    }

    public void restGetRondaTypes(RestResponseListener<RondaType> listener){

        Call<List<RondaTypeDTO>> rondaTypesCall = syncDataService.getRondaTypes();

        rondaTypesCall.enqueue(new Callback<List<RondaTypeDTO>>() {
            @Override
            public void onResponse(Call<List<RondaTypeDTO>> call, Response<List<RondaTypeDTO>> response) {

                List<RondaTypeDTO> data = response.body();

                if(Utilities.listHasElements(data)){
                    try {
                        RondaTypeService rondaTypeService = new RondaTypeServiceImpl(LoadMetadataServiceImpl.APP);
                        Toast.makeText(APP.getApplicationContext(), "Carregando os Tipos de Rondas.", Toast.LENGTH_SHORT).show();
                        List<RondaType> rondaTypes = new ArrayList<>();
                        for (RondaTypeDTO rondaTypeDTO : data){
                            rondaTypeDTO.getRondaType().setSyncStatus(SyncSatus.SENT);
                            rondaTypes.add(rondaTypeDTO.getRondaType());
                        }
                        rondaTypeService.saveOrUpdateRondaTypes(data);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, rondaTypes);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "TIPOS DE RONDAS CARREGADOS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<RondaTypeDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os Tipos de Rondas. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }

}
