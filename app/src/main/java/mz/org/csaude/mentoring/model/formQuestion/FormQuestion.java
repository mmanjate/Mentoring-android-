package mz.org.csaude.mentoring.model.formQuestion;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.form.Form;

public class FormQuestion extends BaseModel {

    private Form form;

    private Question question;

    private boolean mandatory;

    private Integer sequence;

    private Boolean applicable;

    public FormQuestion() {
    }

    public Form getForm() {
        return form;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Boolean getApplicable() {
        return applicable;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }
}
