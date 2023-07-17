package mz.org.csaude.mentoring.model.programmaticArea;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class ProgrammaticArea extends BaseModel {

    private String code;

    private String name;

    private String description;

    public ProgrammaticArea() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
