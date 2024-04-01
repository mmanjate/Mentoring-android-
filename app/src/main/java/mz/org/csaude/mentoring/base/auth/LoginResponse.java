package mz.org.csaude.mentoring.base.auth;

import com.google.gson.annotations.SerializedName;

import mz.org.csaude.mentoring.dto.user.UserDTO;
import mz.org.csaude.mentoring.model.user.User;

public class LoginResponse {

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("username")
    private String username;

    @SerializedName("userUuid")
    private String userUuid;
    @SerializedName("expires_in")
    private Long expires_in;

    @SerializedName("userInfo")
    private UserDTO userInfo;
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UserDTO getUserDTO() {
        return userInfo;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setUserDTO(UserDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
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
