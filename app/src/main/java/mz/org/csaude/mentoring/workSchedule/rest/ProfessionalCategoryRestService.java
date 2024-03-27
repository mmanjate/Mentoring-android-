package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryService;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessionalCategoryRestService extends BaseRestService {

    public ProfessionalCategoryRestService(Application application) {
        super(application);
    }
    public void restGetProfessionalCategory(RestResponseListener<ProfessionalCategory> listener){

       Call<List<ProfessionalCategoryDTO>> callProfessionalCategoryDTO = syncDataService.getProfessionalCategory();

        callProfessionalCategoryDTO.enqueue(new Callback<List<ProfessionalCategoryDTO>>() {
            @Override
            public void onResponse(Call<List<ProfessionalCategoryDTO>> call, Response<List<ProfessionalCategoryDTO>> response) {

               List<ProfessionalCategoryDTO> data = response.body();
               if ( data == null){

                         }
                   try {
                   ProfessionalCategoryService professionalCategoryService = new ProfessionalCategoryServiceImpl(LoadMetadataServiceImpl.APP);
                   Toast.makeText(APP.getApplicationContext(), "Carregando as ProfessionalCategory ", Toast.LENGTH_SHORT).show();
                   professionalCategoryService.saveOrUpdateProfessionalCategorys(data);

                   List<ProfessionalCategory> professionalCategories = new ArrayList<>();

                   for (ProfessionalCategoryDTO professionalCategoryDTO : data){
                       professionalCategories.add(new ProfessionalCategory(professionalCategoryDTO));
                   }
                       listener.doOnResponse(BaseRestService.REQUEST_SUCESS, professionalCategories);
                   } catch (SQLException e) {
                       throw new RuntimeException(e);
                   }
                   Toast.makeText(APP.getApplicationContext(), "ProfessionalCategory carregadas com sucesso!", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<ProfessionalCategoryDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as PROVINCIAS. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }
}
