package mz.org.csaude.mentoring.model.form;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.career.Career;

public class FormTarget extends BaseModel {

    private Form form;

    private Career career;

    private Integer target;

    public FormTarget() {
    }

    public Form getForm() {
        return form;
    }

    public Career getCareer() {
        return career;
    }

    public Integer getTarget() {
        return target;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}
