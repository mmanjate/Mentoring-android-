package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.service.location.HealthFacilityService;
import mz.org.csaude.mentoring.service.location.HealthFacilityServiceImpl;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFacilityRestService extends BaseRestService {
    public HealthFacilityRestService(Application application) {
        super(application);
    }
    public void restGetHealthFacility(RestResponseListener<HealthFacility> listener){

       Call<List<HealthFacilityDTO>> callHealthFacilith = syncDataService.getHealthFacilitys();

        callHealthFacilith.enqueue(new Callback<List<HealthFacilityDTO>>() {
            @Override
            public void onResponse(Call<List<HealthFacilityDTO>> call, Response<List<HealthFacilityDTO>> response) {

             List<HealthFacilityDTO> datas = response.body();

             if(datas == null){

             }
                try {

                    HealthFacilityService healthFacilityService = new HealthFacilityServiceImpl(LoadMetadataServiceImpl.APP);
                    Toast.makeText(APP.getApplicationContext(), "Carregando as HealthFacility ", Toast.LENGTH_SHORT).show();
                    healthFacilityService.savedOrUpdatHealthFacilitys(datas);

                    List<HealthFacility> healthFacilities = new ArrayList<>();

                    for(HealthFacilityDTO healthFacilityDTO : datas){

                        healthFacilities.add(new HealthFacility(healthFacilityDTO));

                    }
                    listener.doOnResponse(BaseRestService.REQUEST_SUCESS, healthFacilities);
                    Toast.makeText(APP.getApplicationContext(), "HealthFacility carregadas com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<List<HealthFacilityDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as HealthFacility. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }
}
