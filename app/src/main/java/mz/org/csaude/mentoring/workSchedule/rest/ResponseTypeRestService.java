package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.location.RondaTypeDTO;
import mz.org.csaude.mentoring.dto.responseType.ResponseTypeDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.responseType.ResponseType;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.responseType.ResponseTypeService;
import mz.org.csaude.mentoring.service.responseType.ResponseTypeServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaTypeService;
import mz.org.csaude.mentoring.service.ronda.RondaTypeServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseTypeRestService extends BaseRestService {
    public ResponseTypeRestService(Application application) {
        super(application);
    }

    public void restGetResponseTypes(RestResponseListener<ResponseType> listener){

        Call<List<ResponseTypeDTO>> responseTypesCall = syncDataService.getResponseTypes();

        responseTypesCall.enqueue(new Callback<List<ResponseTypeDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseTypeDTO>> call, Response<List<ResponseTypeDTO>> response) {

                List<ResponseTypeDTO> data = response.body();

                if(Utilities.listHasElements(data)){
                    try {
                        ResponseTypeService responseTypeService = new ResponseTypeServiceImpl(LoadMetadataServiceImpl.APP);
                        Toast.makeText(APP.getApplicationContext(), "Carregando os Tipos de Respostas.", Toast.LENGTH_SHORT).show();
                        List<ResponseType> responseTypes = new ArrayList<>();
                        for (ResponseTypeDTO responseTypeDTO : data){
                            responseTypeDTO.getResponseType().setSyncStatus(SyncSatus.SENT);
                            responseTypes.add(responseTypeDTO.getResponseType());
                        }
                        responseTypeService.saveOrUpdateResponseTypes(data);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, responseTypes);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "TIPOS DE RESPOSTAS CARREGADOS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseTypeDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os Tipos de Respostas. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }

}
