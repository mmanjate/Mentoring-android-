package mz.org.csaude.mentoring.model.mentorship;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class TimeOfDay extends BaseModel {

    private String description;

    private String code;

    public TimeOfDay() {
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
