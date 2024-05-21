package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.programmaticArea.ProgrammaticAreaDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.service.ProgrammaticArea.ProgrammaticAreaService;
import mz.org.csaude.mentoring.service.ProgrammaticArea.ProgrammaticAreaServiceImpl;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgrammaticAreaRestService extends BaseRestService {
    public ProgrammaticAreaRestService(Application application) {
        super(application);
    }

    public void restGetProgrammaticAreas(RestResponseListener<ProgrammaticArea> listener){

        Call<List<ProgrammaticAreaDTO>> programmaticAreasCall = syncDataService.getAllProgrammaticAreas();

        programmaticAreasCall.enqueue(new Callback<List<ProgrammaticAreaDTO>>() {
            @Override
            public void onResponse(Call<List<ProgrammaticAreaDTO>> call, Response<List<ProgrammaticAreaDTO>> response) {

                List<ProgrammaticAreaDTO> data = response.body();

                if(Utilities.listHasElements(data)){
                    try {
                        ProgrammaticAreaService programmaticAreaService = new ProgrammaticAreaServiceImpl(LoadMetadataServiceImpl.APP);
                        Toast.makeText(APP.getApplicationContext(), "Carregando as Áreas Programáticas.", Toast.LENGTH_SHORT).show();
                        List<ProgrammaticArea> programmaticAreas = new ArrayList<>();
                        for (ProgrammaticAreaDTO programmaticAreaDTO : data){
                            programmaticAreas.add(programmaticAreaDTO.getProgrammaticArea());
                        }
                        programmaticAreaService.saveOrUpdateProgrammaticAreas(data);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, programmaticAreas);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "ÁREAS PROGRAMÁTICAS CARREGADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<ProgrammaticAreaDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as Áreas Programáticas. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }

}
