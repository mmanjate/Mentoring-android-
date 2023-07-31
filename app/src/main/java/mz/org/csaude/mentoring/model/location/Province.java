package mz.org.csaude.mentoring.model.location;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Province extends BaseModel {

    private String description;

    private String code;
    public Province() {
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
