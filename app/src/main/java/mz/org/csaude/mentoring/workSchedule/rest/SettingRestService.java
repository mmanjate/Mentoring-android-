package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingRestService extends BaseRestService {

    public SettingRestService(Application application) {
        super(application);
    }

    public static void restGetSettings(long offSet, long limit, RestResponseListener<Setting> listener){

        Call<List<SettingDTO>> settingsCall = syncDataService.getSettings("0807983dd3b34f109fb75756862d4a72", offSet, limit);

       settingsCall.enqueue(new Callback<List<SettingDTO>>() {
           @Override
           public void onResponse(Call<List<SettingDTO>> call, Response<List<SettingDTO>> response) {
               List<SettingDTO> data = response.body();

               if(data == null){

               }

               try {
                   SettingService settingService = new SettingServiceImpl(LoadMetadataServiceImpl.APP);
                   Toast.makeText(APP.getApplicationContext(), "CARREGANDO OS SETTINGS", Toast.LENGTH_SHORT).show();
                   settingService.savedOrUpdateSettings(data);
                   List<Setting> settings = new ArrayList<>();

                   for(SettingDTO settingDTO : data){
                       settings.add(new Setting(settingDTO));
                   }

                   listener.doOnResponse(BaseRestService.REQUEST_SUCESS, settings);
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }

               Toast.makeText(APP.getApplicationContext(), "SETTINGS carregadas com sucesso", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<List<SettingDTO>> call, Throwable t) {

               Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os Settings", Toast.LENGTH_SHORT).show();
               Log.i("METADATA LOAD --", t.getMessage(), t);
           }
       });

    }
}
