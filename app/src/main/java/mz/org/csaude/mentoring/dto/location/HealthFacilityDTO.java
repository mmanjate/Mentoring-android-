package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.location.HealthFacility;

@NoArgsConstructor
public class HealthFacilityDTO implements Serializable {

    private String uuid;
    private String healthFacility;

    private DistrictDTO districtDTO;

    public HealthFacilityDTO(HealthFacility healthFacility){
        this.setUuid(healthFacility.getUuid());
        this.setHealthFacility(healthFacility.getDescription());
        if (healthFacility.getDistrict() != null) this.setDistrictDTO(new DistrictDTO(healthFacility.getDistrict()));

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(String healthFacility) {
        this.healthFacility = healthFacility;
    }

    public DistrictDTO getDistrictDTO() {
        return districtDTO;
    }

    public void setDistrictDTO(DistrictDTO districtDTO) {
        this.districtDTO = districtDTO;
    }
}
