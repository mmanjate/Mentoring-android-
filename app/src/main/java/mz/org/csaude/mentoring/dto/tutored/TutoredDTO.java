package mz.org.csaude.mentoring.dto.tutored;

import com.fasterxml.jackson.annotation.JsonProperty;
import mz.org.csaude.mentoring.dto.career.CareerDTO;

import java.io.Serializable;

/**
 * @author Jose Julai Ritsure
 */
public class TutoredDTO implements Serializable {

    private String uuid;

    private String code;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private int version;

    @JsonProperty(value = "career")
    private CareerDTO careerDTO;

    public TutoredDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getVersion() {
        return version;
    }

    public CareerDTO getCareerDTO() {
        return careerDTO;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCareerDTO(CareerDTO careerDTO) {
        this.careerDTO = careerDTO;
    }
}
