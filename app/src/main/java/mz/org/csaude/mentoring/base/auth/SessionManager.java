package mz.org.csaude.mentoring.base.auth;

import android.content.Context;
import android.content.SharedPreferences;

import mz.org.csaude.mentoring.R;

public class SessionManager {

    public final static String USER_TOKEN = "user_token";
    private Context context;

    SharedPreferences sharedPref;

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String fetchAuthToken() {
        return sharedPref.getString(USER_TOKEN, null);
    }
}
