package mz.org.csaude.mentoring.model.session;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class SessionStatus extends BaseModel {

    private String description;

    private String code;
    public SessionStatus() {
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
