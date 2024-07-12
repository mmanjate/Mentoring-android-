package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareerRestService extends BaseRestService {
    public CareerRestService(Application application) {
        super(application);
    }

    public void restGetCareers(long offset, long limit, RestResponseListener<Career> listener) {

        Call<List<CareerDTO>> careersCall = syncDataService.getCareers(offset, limit);

        careersCall.enqueue(new Callback<List<CareerDTO>>() {
            @Override
            public void onResponse(Call<List<CareerDTO>> call, Response<List<CareerDTO>> response) {
                List<CareerDTO> data = response.body();
                if(!Utilities.listHasElements(data)){
                    listener.doOnResponse(REQUEST_NO_DATA, null);
                } else {

                    try {
                        CareerService careerService = null;
                        List<Career> careers = new ArrayList<>();
                        for (CareerDTO careerDTO : data) {
                            careerDTO.getCareer().setSyncStatus(SyncSatus.SENT);
                            careers.add(new Career(careerDTO));
                        }
                        careerService.savedOrUpdateCareers(data);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, careers);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<CareerDTO>> call, Throwable t) {
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
    }
}
