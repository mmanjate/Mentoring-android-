package mz.org.csaude.mentoring.model.answer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.answer.AnswerDAOImpl;
import mz.org.csaude.mentoring.dao.setting.SettingDAOImpl;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.indicator.Indicator;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.setting.Setting;

@Data
@DatabaseTable(tableName = Answer.TABLE_NAME, daoClass = AnswerDAOImpl.class)
public class Answer extends BaseModel {

    public static final String TABLE_NAME = "answer";

    public static final String COLUMN_FORM = "form_id";

    public static final String COLUMN_MENTORSHIP = "mentorship_id";

    public static final String COLUMN_QUESTION = "question_id";

    public static final String COLUMN_INDICATOR = "indicator_id";

    public static final String COLUMN_ANSWERTYPE = "answerType_id";

    public static final String COLUMN_VALUE = "value";

    @DatabaseField(columnName = COLUMN_FORM, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Form form;

    @DatabaseField(columnName = COLUMN_MENTORSHIP, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Mentorship mentorship;

    @DatabaseField(columnName = COLUMN_QUESTION, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Question question;

    @DatabaseField(columnName = COLUMN_INDICATOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Indicator indicator;

    @DatabaseField(columnName = COLUMN_ANSWERTYPE, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private AnswerType answerType;

    @DatabaseField(columnName = COLUMN_VALUE)
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
