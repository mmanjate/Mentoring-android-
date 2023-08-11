package mz.org.csaude.mentoring.service.metadata;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class SettingSyncServiceImpl implements SyncService, SettingSyncService {

    private BaseActivity activity;

    /*
    @Inject
    SettingDAO settingDAO;

    @Inject
    public SettingSyncServiceImpl(){
    }

     */

    @Override
    public void execute() {
        MentoringApplication application = (MentoringApplication) activity.getApplication();
        Retrofit retrofit = application.getRetrofit();
        SyncDataService syncDataService = retrofit.create(SyncDataService.class);
        //Call<List<SettingDTO>> call = syncDataService.settings(application.getAuth().getUser().getUuid());

        final ProgressDialog dialog = ProgressDialog.show(activity, "Aguarde", "A receber dados....", true, true);

        /*
        call.enqueue(new Callback<GenericWrapper>() {

                         @Override
                         public void onResponse(Call<GenericWrapper> call, Response<GenericWrapper> response) {

                             GenericWrapper data = response.body();
                             if (data == null) {
                                 Toast.makeText(activity, "Problemas com a sincronização de dados. Verifique o endereço do servidor!", Toast.LENGTH_LONG).show();
                                 return;
                             }

                             List<Setting> settings = new ArrayList<>();
                             if (data.getSettings() != null && data.getSettings().size() > 0) settings.addAll(data.getSettings());
                             if (data.getSetting() != null) settings.add(data.getSetting());

                             processSettings(settings);

                             dialog.dismiss();
                         }

                         @Override
                         public void onFailure(Call<GenericWrapper> call, Throwable t) {
                             dialog.dismiss();
                             Log.e("Connection:--", t.getMessage());
                         }
                     }
        );

         */
    }

    @Override
    public void processSettings(List<SettingDTO> settingsDTOS) {
        if (settingsDTOS != null && !settingsDTOS.isEmpty()) {
            //TODO: remove when implement Injection in all services
            if (activity != null) {
                //settingDAO = new SettingDAOImpl(activity);
            }
/*
            for (settingsDTOS dto : settingsDTOS) {
                if (!settingDAO.exist(setting.getUuid())) {
                    settingDAO.create(setting);
                } else {
                    settingDAO.update(setting);
                }
            }

            settingDAO.close();

 */
        }
    }

    @Override
    public void setActivity(BaseActivity activity) {
             this.activity = activity;
    }
}
