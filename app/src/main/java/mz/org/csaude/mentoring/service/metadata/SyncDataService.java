package mz.org.csaude.mentoring.service.metadata;

import org.json.JSONObject;

import mz.org.csaude.mentoring.base.auth.LoginRequest;
import mz.org.csaude.mentoring.base.auth.LoginResponse;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.career.CareerTypeDTO;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.dto.user.UserDTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/careers/{offset}/{limit}")
    Call<List<CareerDTO>> getCareers(@Path("offset") long offset, @Path("limit") long limit);

    @GET("/tutored")
    Call<List<TutoredDTO>> getTutoreds();

    @POST("/login")
    Call<LoginResponse> login(@Body RequestBody body);

    @GET("/user/getByCredencials/{username}/{password}")
    Call<UserDTO> getByCredencials(@Path("username") final String username, @Path("password") final String password);

}
