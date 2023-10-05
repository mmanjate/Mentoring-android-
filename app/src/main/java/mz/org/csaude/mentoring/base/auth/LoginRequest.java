package mz.org.csaude.mentoring.base.auth;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String username;

    public LoginRequest(String password, String userName) {
        this.password = password;
        this.username = userName;
    }

}
