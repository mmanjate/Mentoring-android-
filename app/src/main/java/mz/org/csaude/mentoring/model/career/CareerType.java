package mz.org.csaude.mentoring.model.career;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class CareerType extends BaseModel {

    private String descripion;

    private String code;

    public CareerType() {
    }

    public String getDescripion() {
        return descripion;
    }

    public String getCode() {
        return code;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
