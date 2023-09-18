package mz.org.csaude.mentoring.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.model.user.UserIndividual;


public class UserDTO {

    private String username;

    private String password;

    private String salt;

    private String type;

    private boolean admin;


    private UserIndividual userIndividual;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.setUsername(user.getUserName());
        this.setPassword(user.getPassword());
        this.setType(user.getType());
        this.setAdmin(user.isAdmin());
        this.setSalt(user.getSalt());
        this.setUserIndividual(user.getUserIndividual());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public UserIndividual getUserIndividual() {
        return userIndividual;
    }

    public void setUserIndividual(UserIndividual userIndividual) {
        this.userIndividual = userIndividual;
    }
}
