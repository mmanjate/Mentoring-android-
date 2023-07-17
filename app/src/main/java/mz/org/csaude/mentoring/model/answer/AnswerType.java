package mz.org.csaude.mentoring.model.answer;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class AnswerType extends BaseModel {

    private String name;
    private String Description;

    public AnswerType() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
