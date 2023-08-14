package mz.org.csaude.mentoring.model.tutor;

import static mz.org.csaude.mentoring.model.tutor.Tutor.COLUMN_TABLE_NAME;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.tutor.TutorDAOImpl;
import mz.org.csaude.mentoring.dao.user.UserDaoImpl;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.partner.Partner;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = COLUMN_TABLE_NAME, daoClass = TutorDAOImpl.class)
public class Tutor extends BaseModel {

  public static final String COLUMN_TABLE_NAME = "tutor";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_CODE = "code";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_SURNAME = "surname";
  public static final String COLUMN_PHONENUMBER = "phone_number";
  public static final String COLUMN_CAREER = "career_id";

  public static final String COLUMN_PARTNER= "partner_id";

  @DatabaseField(columnName = COLUMN_CODE)
  private String code;

  @DatabaseField(columnName = COLUMN_NAME)
  private String name;

  @DatabaseField(columnName = COLUMN_SURNAME)
  private String surname;

  @DatabaseField(columnName = COLUMN_PHONENUMBER)
  private String phoneNumber;

  @DatabaseField(columnName = COLUMN_EMAIL)
  private String email;

  @DatabaseField(columnName = COLUMN_CAREER, canBeNull = false, foreign = true, foreignAutoRefresh = true )
  private Career career;

  @DatabaseField(columnName = COLUMN_PARTNER, canBeNull = false, foreign = true, foreignAutoRefresh = true )
  private Partner partner;

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


  public Partner getPartner() {
    return partner;
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


  public void setPartner(Partner partner) {
    this.partner = partner;
  }

}
