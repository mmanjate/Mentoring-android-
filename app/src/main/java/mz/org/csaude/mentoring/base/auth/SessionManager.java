package mz.org.csaude.mentoring.base.auth;

import android.content.SharedPreferences;

import mz.org.csaude.mentoring.base.application.MentoringApplication;

public class SessionManager {

    public final static String USER_TOKEN = "user_token";

    public final static String REFRESH_TOKEN = "user_refresh_token";
    public final static String TOKEN_EXPIRE_TIME = "token_expiration";
    private MentoringApplication application;

    SharedPreferences sharedPref;

    public SessionManager(MentoringApplication application) {
        this.application = application;
        this.sharedPref = application.getMentoringSharedPreferences();
    }

    public boolean isAccessTokenExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        long accessTokenExpirationTime = getTokenExpiration();
        return accessTokenExpirationTime > 0 && currentTimeMillis >= accessTokenExpirationTime;
    }
    public void saveAuthToken(String token, String refeshToken, long accessTokenExpirationTime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_TOKEN, token);
        editor.putString(REFRESH_TOKEN, refeshToken);
        editor.putLong(TOKEN_EXPIRE_TIME, System.currentTimeMillis() + (accessTokenExpirationTime * 1000));
        editor.apply();
    }

    public String fetchAuthToken() {
        return sharedPref.getString(USER_TOKEN, null);
    }

    public String getRefreshToken() {
        return sharedPref.getString(REFRESH_TOKEN, null);
    }

    public Long getTokenExpiration() {
        return Long.parseLong(sharedPref.getString(TOKEN_EXPIRE_TIME, null));
    }
}
