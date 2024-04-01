package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.partner.Partner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PartnerRestService extends BaseRestService {

    public PartnerRestService(Application application) {
        super(application);
    }

    public  void restGetPartners(RestResponseListener<Partner> listener){

        Call<List<PartnerDTO>> partnerCall= syncDataService.getPartners();

        partnerCall.enqueue(new Callback<List<PartnerDTO>>() {
            @Override
            public void onResponse(Call<List<PartnerDTO>> call, Response<List<PartnerDTO>> response) {

                List<PartnerDTO> data = response.body();

                if(data == null){

                }
                try {

                    List<Partner> partners = new ArrayList<>();
                    for (PartnerDTO partnerDTO : data) {
                        partners.add(new Partner(partnerDTO));
                    }
                    getApplication().getPartnerService().saveAll(partners);

                    listener.doOnResponse(BaseRestService.REQUEST_SUCESS, partners);
                } catch (SQLException e) {
                    listener.doOnRestErrorResponse(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<PartnerDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as ONG's. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
                listener.doOnRestErrorResponse(t.getMessage());

            }
        });


    }
}
