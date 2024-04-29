package mz.org.csaude.mentoring.base.application;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mz.org.csaude.mentoring.base.auth.AuthInterceptorImpl;
import mz.org.csaude.mentoring.common.ApplicationStep;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.employee.EmployeeService;
import mz.org.csaude.mentoring.service.employee.EmployeeServiceImpl;
import mz.org.csaude.mentoring.service.form.FormService;
import mz.org.csaude.mentoring.service.form.FormServiceImpl;
import mz.org.csaude.mentoring.service.location.DistrictService;
import mz.org.csaude.mentoring.service.location.DistrictServiceImpl;
import mz.org.csaude.mentoring.service.location.HealthFacilityService;
import mz.org.csaude.mentoring.service.location.HealthFacilityServiceImpl;
import mz.org.csaude.mentoring.service.location.LocationService;
import mz.org.csaude.mentoring.service.location.LocationServiceImpl;
import mz.org.csaude.mentoring.service.location.ProvinceService;
import mz.org.csaude.mentoring.service.location.ProvinceServiceImpl;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.service.partner.PartnerService;
import mz.org.csaude.mentoring.service.partner.PartnerServiceImpl;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryService;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaMenteeService;
import mz.org.csaude.mentoring.service.ronda.RondaMenteeServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaMentorService;
import mz.org.csaude.mentoring.service.ronda.RondaMentorServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.service.ronda.RondaServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaTypeService;
import mz.org.csaude.mentoring.service.ronda.RondaTypeServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;
import mz.org.csaude.mentoring.service.tutor.TutorService;
import mz.org.csaude.mentoring.service.tutor.TutorServiceImpl;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import mz.org.csaude.mentoring.service.user.UserService;
import mz.org.csaude.mentoring.service.user.UserServiceImpl;
import mz.org.csaude.mentoring.workSchedule.rest.PartnerRestService;
import mz.org.csaude.mentoring.workSchedule.rest.TutoredRestService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MentoringApplication  extends Application {

    private static MentoringApplication mInstance;

    //private static final String BASE_URL = "https://mentdev.fgh.org.mz";

    // 10.10.12.97 http://10.0.2.2:8087  192.168.16.104

    // http://10.10.12.65:8087

    // http://10.10.12.97:8087

    private static final String BASE_URL = "http://10.10.12.97:8087";

    private User authenticatedUser;

    private Tutor tutor;

    private Retrofit retrofit;
    private ObjectMapper mapper;

    private RondaService rondaService;

    private DistrictService districtService;

    private ProvinceService provinceService;

    private SessionService sessionService;

    private TutoredService tutoredService;

    private TutorService tutorService;
    private HealthFacilityService healthFacilityService;

    private FormService formService;

    private MentorshipService mentorshipService;

    private UserService userService;

    private EmployeeService employeeService;

    private PartnerService partnerService;

    private ProfessionalCategoryService professionalCategoryService;

    private ApplicationStep applicationStep;

    private LocationService locationService;

    private TutoredRestService tutoredRestService;
    private RondaMenteeService rondaMenteeService;
    private RondaMentorService rondaMentorService;
    private RondaTypeService rondaTypeService;
    AuthInterceptorImpl interceptor;


    // Rest Services
    private PartnerRestService partnerRestService;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        interceptor = new AuthInterceptorImpl(getApplicationContext());

        mapper = new ObjectMapper();

        setUpRetrofit();

        Locale.setDefault(new Locale("en_ZA"));
    }

    public static synchronized MentoringApplication getInstance() {
        return mInstance;
    }

    public void setUpRetrofit() {

        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public RondaService getRondaService() {
        if (this.rondaService == null) this.rondaService = new RondaServiceImpl(this);
        return rondaService;
    }

    public DistrictService getDistrictService() {
        if (this.districtService == null) this.districtService = new DistrictServiceImpl(this);
        return districtService;
    }

    public ProvinceService getProvinceService() {
        if (this.provinceService == null) this.provinceService = new ProvinceServiceImpl(this);
        return provinceService;
    }

    public HealthFacilityService getHealthFacilityService() {
        if (this.healthFacilityService == null) this.healthFacilityService = new HealthFacilityServiceImpl(this);
        return healthFacilityService;
    }

    public SessionService getSessionService() {
        if (this.sessionService == null) this.sessionService = new SessionServiceImpl(this);
        return sessionService;
    }

    public TutorService getTutorService() {
        if (this.tutorService == null) this.tutorService = new TutorServiceImpl(this);
        return tutorService;
    }

    public TutoredService getTutoredService() {
        if (this.tutoredService == null) this.tutoredService = new TutoredServiceImpl(this);
        return tutoredService;
    }

    public MentorshipService getMentorshipService() {
        if (this.mentorshipService == null) this.mentorshipService = new MentorshipServiceImpl(this);
        return mentorshipService;
    }

    public FormService getFormService() {
        if (this.formService == null) this.formService = new FormServiceImpl(this);
        return formService;
    }

    public UserService getUserService() {
        if (this.userService == null) this.userService = new UserServiceImpl(this);
        return userService;
    }

    public EmployeeService getEmployeeService() {
        if (this.employeeService == null) this.employeeService = new EmployeeServiceImpl(this);
        return employeeService;
    }

    public PartnerService getPartnerService() {
        if (this.partnerService == null) this.partnerService = new PartnerServiceImpl(this);
        return partnerService;
    }

    public ProfessionalCategoryService getProfessionalCategoryService() {
        if (this.professionalCategoryService == null) this.professionalCategoryService = new ProfessionalCategoryServiceImpl(this);
        return professionalCategoryService;
    }

    public LocationService getLocationService() {
        if (this.locationService == null) this.locationService = new LocationServiceImpl(this);
        return locationService;
    }

    public PartnerRestService getPartnerRestService() {
        if (partnerRestService == null) this.partnerRestService = new PartnerRestService(this);
        return partnerRestService;
    }

    public TutoredRestService getTutoredRestService() {
        if (tutoredRestService == null) this.tutoredRestService = new TutoredRestService(this);
        return tutoredRestService;
    }

    public RondaMenteeService getRondaMenteeService() {
        if (this.rondaMenteeService == null) this.rondaMenteeService = new RondaMenteeServiceImpl(this);
        return rondaMenteeService;
    }

    public RondaMentorService getRondaMentorService() {
        if (this.rondaMentorService == null) this.rondaMentorService = new RondaMentorServiceImpl(this);
        return rondaMentorService;
    }
    public RondaTypeService getRondaTypeService() {
        if (this.rondaTypeService == null) this.rondaTypeService = new RondaTypeServiceImpl(this);
        return rondaTypeService;
    }

    public ApplicationStep getApplicationStep() {
        return this.applicationStep;
    }

    public Tutor getCurrMentor() {
        return this.tutor;
    }

    public void setCurrTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void init() throws SQLException {
        this.applicationStep = ApplicationStep.fastCreate(ApplicationStep.STEP_INIT);
        setCurrTutor(getTutorService().getByEmployee(getAuthenticatedUser().getEmployee()));
        getCurrMentor().getEmployee().setLocations(getLocationService().getAllOfEmploee(getAuthenticatedUser().getEmployee()));
    }
}
