package mz.org.csaude.mentoring.model.form;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class FormType extends BaseModel {

    private String description;

    private  String code;

    public FormType() {
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
