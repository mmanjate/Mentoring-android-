package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.resource.ResourceDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.resourceea.Resource;
import mz.org.csaude.mentoring.service.resource.ResourceService;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceRestService extends BaseRestService {

    public ResourceRestService(Application application) {
        super(application);
    }

   public void restGetResources(RestResponseListener<Resource> listener){

       Call<List<ResourceDTO>> resourceCall = syncDataService.getAllResource();

       resourceCall.enqueue(new Callback<List<ResourceDTO>>() {
           @Override
           public void onResponse(Call<List<ResourceDTO>> call, Response<List<ResourceDTO>> response) {

              List<ResourceDTO> data = response.body();

              if(Utilities.listHasElements(data)){
                  try {
                  ResourceService resourceService = getApplication().getResourceService();
                  Toast.makeText(APP.getApplicationContext(), "Carregando os Resources .", Toast.LENGTH_SHORT).show();
                  List<Resource> resourceList = new ArrayList<>();

                  for(ResourceDTO resourceDTO : data){
                      resourceList.add(resourceDTO.getResourceModel());
                  }
                      resourceService.savedOrUpdateResources(resourceList);
                      listener.doOnResponse(BaseRestService.REQUEST_SUCESS, resourceList);
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  Toast.makeText(APP.getApplicationContext(), "RESOURCE DE EA carregadas com sucesso!", Toast.LENGTH_SHORT).show();

              }else{
                  listener.doOnResponse(REQUEST_NO_DATA, null);
              }
           }

           @Override
           public void onFailure(Call<List<ResourceDTO>> call, Throwable t) {
               Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os Recursos de EA. Tente mais tarde....", Toast.LENGTH_SHORT).show();
               Log.i("METADATA LOAD --", t.getMessage(), t);

           }
       });

   }
}
