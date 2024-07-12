package mz.org.csaude.mentoring.base.auth;


import static mz.org.csaude.mentoring.base.application.MentoringApplication.BASE_URL;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import mz.org.csaude.mentoring.service.metadata.SyncDataService;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthInterceptorImpl implements Interceptor {

    private SessionManager sessionManager;
    private Context context;
    private static final int NEW_EXPIRATION_TIME = 6000;

    public AuthInterceptorImpl(Context context) {
        this.context = context;
        this.sessionManager = new SessionManager((MentoringApplication) context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String accessToken = sessionManager.fetchAuthToken();

        // Check if the access token is expired and refresh it if necessary
        if (accessToken != null && sessionManager.isAccessTokenExpired()) {
            String refreshToken = sessionManager.getRefreshToken();
            // Assume you have a method to refresh the token
            accessToken = refreshToken(refreshToken);
            if (accessToken == null) {
                throw new IOException("Unable to refresh token");
            }
        }

        Request newRequest = chain.request().newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();

        return chain.proceed(newRequest);
    }

    private String refreshToken(String refreshToken) {
        // Convert refresh token to JSON or other required format
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{\"refresh_token\":\"" + refreshToken + "\"}");

        // Create Retrofit instance for auth service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) // Make sure to define your BASE_URL
                .client(new OkHttpClient()) // Use a new client or configure it similarly to your main client
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SyncDataService authService = retrofit.create(SyncDataService.class);
        try {
            retrofit2.Response<ResponseBody> response = authService.refreshToken(body).execute();
            if (response.isSuccessful() && response.body() != null) {
                // Assuming the response is just the new token
                //String newToken = response.body().string();
                //sessionManager.saveAuthToken(newToken, refreshToken, NEW_EXPIRATION_TIME); // Define NEW_EXPIRATION_TIME
                return handleTokenResponse(response.body().string());
            } else {
                // Handle the scenario where the refresh token is no longer valid
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String handleTokenResponse(String jsonResponse) {
        // Parse the JSON response
        Gson gson = new Gson();
        Type listType = new TypeToken<List<TokenResponse>>() {}.getType();
        List<TokenResponse> tokenResponses = gson.fromJson(jsonResponse, listType);

        if (tokenResponses != null && !tokenResponses.isEmpty()) {
            TokenResponse tokenResponse = tokenResponses.get(0);

            // Save the tokens in the SessionManager
            sessionManager.saveAuthToken(
                    tokenResponse.getAccessToken(),
                    tokenResponse.getRefreshToken(),
                    NEW_EXPIRATION_TIME
            );
            return tokenResponse.getAccessToken();
        }
        return null;
    }

}
