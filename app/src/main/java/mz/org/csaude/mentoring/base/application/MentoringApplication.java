package mz.org.csaude.mentoring.base.application;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mz.org.csaude.mentoring.base.auth.AuthInterceptorImpl;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.form.FormService;
import mz.org.csaude.mentoring.service.form.FormServiceImpl;
import mz.org.csaude.mentoring.service.location.DistrictService;
import mz.org.csaude.mentoring.service.location.DistrictServiceImpl;
import mz.org.csaude.mentoring.service.location.HealthFacilityService;
import mz.org.csaude.mentoring.service.location.HealthFacilityServiceImpl;
import mz.org.csaude.mentoring.service.location.ProvinceService;
import mz.org.csaude.mentoring.service.location.ProvinceServiceImpl;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.service.ronda.RondaServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;
import mz.org.csaude.mentoring.service.tutor.TutorService;
import mz.org.csaude.mentoring.service.tutor.TutorServiceImpl;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MentoringApplication  extends Application {

    private static MentoringApplication mInstance;

    //private static final String BASE_URL = "https://mentdev.fgh.org.mz";

    // 10.10.12.97 http://10.0.2.2:8087  192.168.16.104

    private static final String BASE_URL = "http://10.10.12.65:8087";

    private User authenticatedUser;

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
    AuthInterceptorImpl interceptor;

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
}
