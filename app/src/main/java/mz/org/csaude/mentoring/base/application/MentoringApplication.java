package mz.org.csaude.mentoring.base.application;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mz.org.csaude.mentoring.base.auth.AuthInterceptorImpl;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.service.ronda.RondaServiceImpl;
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
}
