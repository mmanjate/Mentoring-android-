package mz.org.csaude.mentoring.base.auth;

import com.google.gson.annotations.SerializedName;

import mz.org.csaude.mentoring.dto.user.UserDTO;
import mz.org.csaude.mentoring.model.user.User;

public class LoginResponse {

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("username")
    private String username;

    @SerializedName("expires_in")
    private Long expires_in;

    @SerializedName("userInfo")
    private UserDTO userDTO;
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}
