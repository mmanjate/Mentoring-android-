package mz.org.csaude.mentoring.model.tutor;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.partner.Partner;

public class Tutor extends BaseModel {

  private String code;

  private String name;

  private String surname;

  private Career career;

  private String phoneNumber;

  private String email;

  private Boolean isUser = Boolean.FALSE;

  private Partner partner;

  private Boolean isAdmin;

  public Tutor() {
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

  public Career getCareer() {
    return career;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public Boolean getUser() {
    return isUser;
  }

  public Partner getPartner() {
    return partner;
  }

  public Boolean getAdmin() {
    return isAdmin;
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

  public void setCareer(Career career) {
    this.career = career;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUser(Boolean user) {
    isUser = user;
  }

  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }
}
