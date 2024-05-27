package mz.org.csaude.mentoring.model.employee;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.employee.EmployeeDAOImpl;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.dto.location.LocationDTO;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;
import mz.org.csaude.mentoring.util.Utilities;


@DatabaseTable(tableName = Employee.TABLE_NAME, daoClass = EmployeeDAOImpl.class)
public class Employee extends BaseModel implements Listble {

    public static final String TABLE_NAME = "employee";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";

    public static final String COLUMN_NUIT = "nuit";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PROFESSIONAL_CATEGORY = "professional_category_id";

    public static final String COLUMN_TRAINING_YEAR = "training_year";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PARTNER = "partner_id";

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;
    @DatabaseField(columnName = COLUMN_SURNAME)
    private String surname;

    @DatabaseField(columnName = COLUMN_NUIT, unique = true)
    private long nuit;
    @DatabaseField(columnName = COLUMN_PROFESSIONAL_CATEGORY, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ProfessionalCategory professionalCategory;

    @DatabaseField(columnName = COLUMN_TRAINING_YEAR)
    private int trainingYear;

    @DatabaseField(columnName = COLUMN_PHONE_NUMBER, unique = true)
    private String phoneNumber;

    @DatabaseField(columnName = COLUMN_EMAIL, unique = true)
    private String email;

    @DatabaseField(columnName = COLUMN_PARTNER, foreign = true, foreignAutoRefresh = true)
    private Partner partner;

    private List<Location> locations;

    public Employee() {
    }

    public Employee(String name, String surname, int nuit, ProfessionalCategory professionalCategory, int trainingYear, String phoneNumber, String email, Partner partner) {
        this.name = name;
        this.surname = surname;
        this.nuit = nuit;
        this.professionalCategory = professionalCategory;
        this.trainingYear = trainingYear;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.partner = partner;
    }

    public Employee(String name, String surname, int nuit, ProfessionalCategory professionalCategory, int trainingYear, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.nuit = nuit;
        this.professionalCategory = professionalCategory;
        this.trainingYear = trainingYear;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Employee(EmployeeDTO employeeDTO) {
        this.setUuid(employeeDTO.getUuid());
        this.setName(employeeDTO.getName());
        this.setSurname(employeeDTO.getSurname());
        this.setNuit(employeeDTO.getNuit());
        this.setTrainingYear(employeeDTO.getTrainingYear());
        this.setEmail(employeeDTO.getEmail());
        this.setPhoneNumber(employeeDTO.getPhoneNumber());
        this.setLocations(retriveLocations(employeeDTO.getLocationDTOSet()));
        if(employeeDTO.getProfessionalCategoryDTO() != null) this.setProfessionalCategory(new ProfessionalCategory(employeeDTO.getProfessionalCategoryDTO()));
        if(employeeDTO.getPartnerDTO() != null) this.setPartner(new Partner(employeeDTO.getPartnerDTO()));
    }

    private List<Location> retriveLocations(List<LocationDTO> locationDTOSet) {
        List<Location> locations = new ArrayList<>();
        for (LocationDTO locationDTO : locationDTOSet) {
            Location location = new Location(locationDTO, this);
            locations.add(location);
        }
        return locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getNuit() {
        return nuit;
    }

    public void setNuit(long nuit) {
        this.nuit = nuit;
    }

    public ProfessionalCategory getProfessionalCategory() {
        return professionalCategory;
    }

    public void setProfessionalCategory(ProfessionalCategory professionalCategory) {
        this.professionalCategory = professionalCategory;
    }

    public int getTrainingYear() {
        return  trainingYear;
    }

    public void setTrainingYear(int trainingYear) {
        this.trainingYear = trainingYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }



    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getDrawable() {
        return 0;
    }

    @Override
    public String getCode() {
        return null;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    @Override
    public String validade() {
        if(StringUtils.isEmpty(getName())) return "Campo nome nao pode estar vazio";
        if(StringUtils.isEmpty(getSurname())) return "Campo apelido nao pode estar vazio";
        if(StringUtils.isEmpty(getPhoneNumber())) return "Campo Telefone nao pode estar vazio";
        if(StringUtils.isEmpty(getEmail())) return "Campo Email nao pode estar vazio";
        if(this.getProfessionalCategory() == null) return "Campo Categoria Professional nao pode estar vazio";
        if(!Utilities.listHasElements(this.locations)) return "Por favor indicar a unidade sanitária.";
        return super.validade();
    }

    public void addLocation(Location location) {
        if (this.locations == null) this.locations = new ArrayList<>();
        location.setEmployee(this);
        this.locations.add(location);
    }
}
