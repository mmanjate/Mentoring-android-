package mz.org.csaude.mentoring.model.partner;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Partner extends BaseModel {

    private String name;

    private String description;

    public Partner() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
