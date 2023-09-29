package mz.org.csaude.mentoring.dto.tutor;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.dto.user.UserDTO;

public class TutorDTO implements Serializable {

    private String uuid;

    private String code;

    private String name;

    private String sunname;

    @JsonProperty(value = "careerDTO")
    private CareerDTO careerDTO;

    private String phoneNumber;

    private String email;

    private boolean isUser;


    @JsonProperty(value = "partnerDTO")
    private PartnerDTO partnerDTO;

    @JsonProperty(value = "userDTO")
    private UserDTO userDTO;


    public TutorDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSunname() {
        return sunname;
    }

    public CareerDTO getCareerDTO() {
        return careerDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isUser() {
        return isUser;
    }

    public PartnerDTO getPartnerDTO() {
        return partnerDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSunname(String sunname) {
        this.sunname = sunname;
    }

    public void setCareerDTO(CareerDTO careerDTO) {
        this.careerDTO = careerDTO;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public void setPartnerDTO(PartnerDTO partnerDTO) {
        this.partnerDTO = partnerDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
