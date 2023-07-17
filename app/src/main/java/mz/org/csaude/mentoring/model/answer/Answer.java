package mz.org.csaude.mentoring.model.answer;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.indicator.Indicator;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public class Answer extends BaseModel {

    private Form form;

    private Mentorship mentorship;

    private Question question;

    private Indicator indicator;

    private AnswerType answerType;

    private String value;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Answer() {
    }

    public Form getForm() {
        return form;
    }

    public Mentorship getMentorship() {
        return mentorship;
    }

    public Question getQuestion() {
        return question;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setMentorship(Mentorship mentorship) {
        this.mentorship = mentorship;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }
}
