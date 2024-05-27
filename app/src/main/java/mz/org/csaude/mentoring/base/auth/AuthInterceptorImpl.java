package mz.org.csaude.mentoring.base.auth;

import android.content.Context;

import java.io.IOException;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptorImpl implements Interceptor {

    private SessionManager sessionManager;
    private Context context;

    public AuthInterceptorImpl(Context context) {
        this.context = context;
        this.sessionManager = new SessionManager((MentoringApplication) context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String accessToken = sessionManager.fetchAuthToken();

        /*if (accessToken != null && sessionManager.isAccessTokenExpired()) {
            String refreshToken = sessionManager.getRefreshToken();

            String refreshedToken = runBlo
        }*/
        Request newRequest = chain.request().newBuilder()
                .header("Authorization","Bearer "+ this.sessionManager.fetchAuthToken())
                .build();

        return chain.proceed(newRequest);
    }
}
