package mz.org.csaude.mentoring.service.metadata;

import mz.org.csaude.mentoring.base.auth.LoginResponse;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.career.CareerTypeDTO;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.dto.location.DistrictDTO;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
import mz.org.csaude.mentoring.dto.location.ProvinceDTO;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.dto.tutor.TutorDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.dto.user.UserDTO;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by ialuj
 */
public interface SyncDataService {

    @GET("/settings/tutor/{uuid}/{offset}/{limit}")
    Call<List<SettingDTO>> getSettings(@Path("uuid") final String uuid, @Path("offset") long offset, @Path("limit") long limit);

    @GET("/healthFacilities/tutor/{uuid}")
    Call<List<HealthFacilityDTO>> getHealthFacilities(@Path("uuid") final String uuid);

    @GET("/cabinets/{offset}/{limit}")
    Call<List<CabinetDTO>> getCabinets(@Path("offset") long offset, @Path("limit") long limit);

    @GET("/careers/career-types")
    Call<List<CareerTypeDTO>> getCareerTypes();

    @GET("/careers/{offset}/{limit}")
    Call<List<CareerDTO>> getCareers(@Path("offset") long offset, @Path("limit") long limit);

    @GET("/tutored/getTutoreds")
    Call<List<TutoredDTO>> getTutoreds(@Query("uuids") List<String> uuids);
    @POST("/login")
    Call<LoginResponse> login(@Body RequestBody body);

   /* @POST("users/refresh-token/")
    suspend fun refreshAccessToken(): TokenResponse*/

    @GET("/user/getByCredencials/{username}/{password}")
    Call<UserDTO> getByCredencials(@Path("username") final String username, @Path("password") final String password);

    @GET("/tutor/tutors/{limit}/{offset}")
    Call<List<TutorDTO>> getTutors(@Path("limit") long limit , @Path("offset") long offset);

    @GET("/mentor/employee/{uuid}")
    Call<TutorDTO> getTutorByEmployeeUuid(@Path("uuid") String userUuid);

    @GET("/province/getall")
    Call<List<ProvinceDTO>> getProvinces();

    @GET("healthfacility/getall")
    Call<List<HealthFacilityDTO>> getHealthFacilitys();

    @GET("healthfacility/getByDistricts")
    Call<List<HealthFacilityDTO>> getByDistricts(@Query("uuids") List<String> uuids);

    @GET("district/getall")
    Call<List<DistrictDTO>> getDistricts();

    @GET("professionalcategory/getall")
    Call<List<ProfessionalCategoryDTO>> getProfessionalCategory();


    @GET("partner/getall")
    Call<List<PartnerDTO>> getPartners();

    @POST("tutored/save")
    Call<List<TutoredDTO>> postTutoreds(@Body List<TutoredDTO> tutoredDTOS);
}
