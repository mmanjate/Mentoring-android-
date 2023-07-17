package mz.org.csaude.mentoring.model.location;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class HealthFacility extends BaseModel {

    private District district;

    private String healthFacility;

    public HealthFacility() {
    }

    public District getDistrict() {
        return district;
    }

    public String getHealthFacility() {
        return healthFacility;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setHealthFacility(String healthFacility) {
        this.healthFacility = healthFacility;
    }
}
