package mz.org.csaude.mentoring.service.metadata;

import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by ialuj
 */
public interface SyncDataService {

    @GET("/settings/tutor/{uuid}")
    Call<List<SettingDTO>> getSettings(@Path("uuid") final String uuid);
}
