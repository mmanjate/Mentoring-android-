package mz.org.csaude.mentoring.base.auth;

import android.content.Context;
import android.content.SharedPreferences;

import mz.org.csaude.mentoring.R;

public class SessionManager {

    public final static String USER_TOKEN = "user_token";
    public final static String TOKEN_EXPIRE_TIME = "token_expiration";
    private Context context;

    SharedPreferences sharedPref;

    private long accessTokenExpirationTime = 0;

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public boolean isAccessTokenExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        return accessTokenExpirationTime > 0 && currentTimeMillis >= accessTokenExpirationTime;
    }
    public void saveAuthToken(String token, long accessTokenExpirationTime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_TOKEN, token);
        editor.putLong(TOKEN_EXPIRE_TIME, System.currentTimeMillis() + (accessTokenExpirationTime * 1000));
        editor.apply();
    }

    public String fetchAuthToken() {
        return sharedPref.getString(USER_TOKEN, null);
    }
}
