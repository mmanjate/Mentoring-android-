package mz.org.csaude.mentoring.model.setting;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Setting extends BaseModel {

    private String designation;

    private Integer value;

    private String description;

    public Setting() {
    }

    public String getDesignation() {
        return designation;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
