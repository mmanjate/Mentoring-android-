package mz.org.csaude.mentoring.service.metadata;

import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.career.CareerTypeDTO;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
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

    @GET("/healthFacilities/tutor/{uuid}")
    Call<List<HealthFacilityDTO>> getHealthFacilities(@Path("uuid") final String uuid);

    @GET("/cabinets")
    Call<List<CabinetDTO>> getCabinets();

    @GET("/careers/career-types")
    Call<List<CareerTypeDTO>> getCareerTypes();

    @GET("/careers")
    Call<List<CareerDTO>> getCareers();
}
