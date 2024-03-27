package mz.org.csaude.mentoring.dto.employee;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.dto.location.LocationDTO;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.partner.Partner;

@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private String uuid;
    private String name;

    private String surname;

    private int nuit;

    private ProfessionalCategoryDTO professionalCategoryDTO;

    private int trainingYear;

    private String phoneNumber;

    private String email;

    private PartnerDTO partnerDTO;

    private Set<LocationDTO> locationDTOSet;

    public EmployeeDTO(Employee employee) {
        this.setUuid(employee.getUuid());
        this.setName(employee.getName());
        this.setSurname(employee.getSurname());
        this.setNuit(employee.getNuit());
        this.setEmail(employee.getEmail());
        this.setPhoneNumber(employee.getPhoneNumber());
        this.setTrainingYear(employee.getTrainingYear());
        this.setLocationDTOSet( setLocations(employee.getLocations()));
        if(employee.getProfessionalCategory() != null) this.setProfessionalCategoryDTO(new ProfessionalCategoryDTO(employee.getProfessionalCategory()));
       if(employee.getPartner() != null) this.setPartnerDTO(new PartnerDTO((Partner) employee.getPartner()));
    }

    private Set<LocationDTO> setLocations(Set<Location> locationSet) {
        Set<LocationDTO> locationDTOSet = new HashSet<>();

        for (Location location : locationSet) {
            locationDTOSet.add(new LocationDTO(location));
        }
        return locationDTOSet;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public int getNuit() {
        return nuit;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public int getTrainingYear() {
        return trainingYear;
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

    public ProfessionalCategoryDTO getProfessionalCategoryDTO() {
        return professionalCategoryDTO;
    }

    public void setProfessionalCategoryDTO(ProfessionalCategoryDTO professionalCategoryDTO) {
        this.professionalCategoryDTO = professionalCategoryDTO;
    }

    public PartnerDTO getPartnerDTO() {
        return partnerDTO;
    }

    public void setPartnerDTO(PartnerDTO partnerDTO) {
        this.partnerDTO = partnerDTO;
    }

    public Set<LocationDTO> getLocationDTOSet() {
        return locationDTOSet;
    }

    public void setLocationDTOSet(Set<LocationDTO> locationDTOSet) {
        this.locationDTOSet = locationDTOSet;
    }
}
