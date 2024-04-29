package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.mentorship.TimeOfDayDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;
import mz.org.csaude.mentoring.service.mentorship.TimeOfDayService;
import mz.org.csaude.mentoring.service.mentorship.TimeOfDayServiceImpl;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeOfDayRestService extends BaseRestService {
    public TimeOfDayRestService(Application application) {
        super(application);
    }

    public void restGetTimesOfDay(RestResponseListener<TimeOfDay> listener){

        Call<List<TimeOfDayDTO>> timesOfDayCall = syncDataService.getTimesOfDay();

        timesOfDayCall.enqueue(new Callback<List<TimeOfDayDTO>>() {
            @Override
            public void onResponse(Call<List<TimeOfDayDTO>> call, Response<List<TimeOfDayDTO>> response) {

                List<TimeOfDayDTO> data = response.body();

                if(Utilities.listHasElements(data)){
                    try {
                        TimeOfDayService timeOfDayService = new TimeOfDayServiceImpl(LoadMetadataServiceImpl.APP);
                        Toast.makeText(APP.getApplicationContext(), "Carregando os Período do Dia.", Toast.LENGTH_SHORT).show();
                        timeOfDayService.saveOrUpdateTimesOfDay(data);
                        List<TimeOfDay> timeOfDays = new ArrayList<>();
                        for (TimeOfDayDTO timeOfDayDTO : data){
                            timeOfDays.add(timeOfDayDTO.getTimeOfDay());
                        }
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, timeOfDays);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(APP.getApplicationContext(), "PERÍODOS DO DIA CARREGADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<TimeOfDayDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os Períodos do Dia. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }

}
