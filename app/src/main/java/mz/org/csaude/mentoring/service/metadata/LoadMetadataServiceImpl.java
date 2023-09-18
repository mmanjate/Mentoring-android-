package mz.org.csaude.mentoring.service.metadata;

import android.app.Application;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.service.career.CareerServiceImpl;
import mz.org.csaude.mentoring.service.location.CabinetService;
import mz.org.csaude.mentoring.service.location.CabinetServiceImpl;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.sql.SQLException;
import java.util.List;

public class LoadMetadataServiceImpl extends BaseRestService implements LoadMetadataService {

    public LoadMetadataServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void load() {

        SyncDataService syncDataService = getRetrofit().create(SyncDataService.class);

        Call<List<SettingDTO>> call = syncDataService.getSettings("49dcfc96e18644d1ae9e82dbb7e873a1");

        call.enqueue(new Callback<List<SettingDTO>>() {
            @Override
            public void onResponse(Call<List<SettingDTO>> call, Response<List<SettingDTO>> response) {
                List<SettingDTO> data = response.body();

                if (data == null) {
                    // to do...
                }

                try {
                    SettingService settingService = new SettingServiceImpl(LoadMetadataServiceImpl.APP);
                    Toast.makeText(APP.getApplicationContext(), "Carregando os SETTINGS...", Toast.LENGTH_SHORT).show();
                    settingService.savedOrUpdateSettings(data);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(APP.getApplicationContext(), "SETTINGS carregados com sucesso!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<SettingDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar metadados. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

        Call<List<CabinetDTO>> cabinetsCall = syncDataService.getCabinets();

        cabinetsCall.enqueue(new Callback<List<CabinetDTO>>() {

            @Override
            public void onResponse(Call<List<CabinetDTO>> call, Response<List<CabinetDTO>> response) {
                List<CabinetDTO> data = response.body();

                if (data == null) {
                    // to do...
                }

                try {
                    CabinetService cabinetService = new CabinetServiceImpl(LoadMetadataServiceImpl.APP);
                    Toast.makeText(APP.getApplicationContext(), "Carregando os CABINETS...", Toast.LENGTH_SHORT).show();
                    cabinetService.saveOrUpdateCabinets(data);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(APP.getApplicationContext(), "CABINETS carregados com sucesso!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<CabinetDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os CABINETS. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }

        });

        Call<List<CareerDTO>> careersCall = syncDataService.getCareers(0, 200);

        careersCall.enqueue(new Callback<List<CareerDTO>>() {
            @Override
            public void onResponse(Call<List<CareerDTO>> call, Response<List<CareerDTO>> response) {
                List<CareerDTO> data = response.body();
                if (data == null) {
                    // to do...
                }

                try {
                    CareerService careerService = new CareerServiceImpl(LoadMetadataServiceImpl.APP);
                    Toast.makeText(APP.getApplicationContext(), "Carregando as CARREIRAS...", Toast.LENGTH_SHORT).show();
                    careerService.savedOrUpdateCareers(data);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(APP.getApplicationContext(), "CARREIRAS carregadas com sucesso!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<CareerDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os TIPOS DE CARREIRAS. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

        Call<List<TutoredDTO>> tutoredsCall = syncDataService.getTutoreds();

        tutoredsCall.enqueue(new Callback<List<TutoredDTO>>() {
            @Override
            public void onResponse(Call<List<TutoredDTO>> call, Response<List<TutoredDTO>> response) {
                List<TutoredDTO> data = response.body();
                if (data == null) {
                    // to do...
                }

                try {
                    TutoredService tutoredService = new TutoredServiceImpl(LoadMetadataServiceImpl.APP);
                    Toast.makeText(APP.getApplicationContext(), "Carregando os TUTORADOS...", Toast.LENGTH_SHORT).show();
                    tutoredService.savedOrUpdateTutoreds(data);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(APP.getApplicationContext(), "TUTORADOS carregados com sucesso!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<TutoredDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar os tutorados. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });

    }
}
