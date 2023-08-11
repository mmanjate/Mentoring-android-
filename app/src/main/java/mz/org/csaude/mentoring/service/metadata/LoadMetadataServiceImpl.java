package mz.org.csaude.mentoring.service.metadata;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoadMetadataServiceImpl extends BaseRestService implements LoadMetadataService {
    @Override
    public void load(BaseActivity activity, ProgressDialog progressDialog, User currentUser) {

        LoadMetadataServiceImpl.APP = (MentoringApplication) activity.getApplication();

        Retrofit retrofit = LoadMetadataServiceImpl.APP.getRetrofit();

        SyncDataService syncDataService = retrofit.create(SyncDataService.class);

            Call<List<SettingDTO>> call = syncDataService.getSettings("49dcfc96e18644d1ae9e82dbb7e873a1");

            call.enqueue(new Callback<List<SettingDTO>>() {
                @Override
                public void onResponse(Call<List<SettingDTO>> call, Response<List<SettingDTO>> response) {
                    List<SettingDTO> data = response.body();

                    if(data==null){
                        // to do...
                    }

                    SettingService settingService = null;
                    try {
                        settingService = new SettingServiceImpl(LoadMetadataServiceImpl.APP);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Toast.makeText(activity, "Carregando os SETTINGS...", Toast.LENGTH_SHORT).show();
                        settingService.savedOrUpdateSettings(data);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    Toast.makeText(activity, "Metadados carregados com sucesso!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<List<SettingDTO>> call, Throwable t) {
                    Toast.makeText(activity, "Não foi possivel carregar metadados. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                    Log.i("METADATA LOAD --", t.getMessage(), t);
                }
            });

    }
}
