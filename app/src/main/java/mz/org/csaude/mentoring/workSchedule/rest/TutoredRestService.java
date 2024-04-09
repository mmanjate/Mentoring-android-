package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutoredRestService extends BaseRestService {


    public TutoredRestService(Application application) {
        super(application);
    }

    public void restGetTutored(RestResponseListener<Tutored> listener){
        List<String> uuids = new ArrayList<>();
        for (Location location : getApplication().getCurrMentor().getEmployee().getLocations()) {
            uuids.add(location.getHealthFacility().getUuid());
        }
        Call<List<TutoredDTO>> tutoredCall = syncDataService.getTutoreds(uuids);

        tutoredCall.enqueue(new Callback<List<TutoredDTO>>() {
            @Override
            public void onResponse(Call<List<TutoredDTO>> call, Response<List<TutoredDTO>> response) {
                List<TutoredDTO> data = response.body();
                if (Utilities.listHasElements(data)) {
                    try {
                        List<Tutored> tutoreds = Utilities.parse(data, Tutored.class);
                        for (Tutored tutored : tutoreds) { tutored.setSyncStatus(SyncSatus.SENT);}
                        getApplication().getTutoredService().savedOrUpdateTutoreds(tutoreds);
                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, tutoreds);
                    } catch (SQLException | InstantiationException | IllegalAccessException |
                             InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TutoredDTO>> call, Throwable t) {
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
    }


    public void restPostTutored(RestResponseListener<Tutored> listener){

        List<Tutored> tutoreds = null;
        try {
            tutoreds = getApplication().getTutoredService().getAllNotSynced();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Utilities.listHasElements(tutoreds)) {
            Call<List<TutoredDTO>> tutoredCall = syncDataService.postTutoreds(Utilities.parseList(tutoreds, TutoredDTO.class));
            tutoredCall.enqueue(new Callback<List<TutoredDTO>>() {
                @Override
                public void onResponse(Call<List<TutoredDTO>> call, Response<List<TutoredDTO>> response) {
                    response.body();
                    listener.doOnResponse(BaseRestService.REQUEST_SUCESS, new ArrayList<>());
                }

                @Override
                public void onFailure(Call<List<TutoredDTO>> call, Throwable t) {
                    Log.i("METADATA LOAD --", t.getMessage(), t);
                }
            });
        }

    }

}
