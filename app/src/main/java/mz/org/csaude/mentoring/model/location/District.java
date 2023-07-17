package mz.org.csaude.mentoring.model.location;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class District extends BaseModel {

    private Province province;

    private String district;

    public District() {
    }

    public Province getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
