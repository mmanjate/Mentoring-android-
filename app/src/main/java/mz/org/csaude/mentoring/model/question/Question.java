package mz.org.csaude.mentoring.model.question;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.question.QuestionDAOImpl;
import mz.org.csaude.mentoring.dto.question.QuestionDTO;

@DatabaseTable(tableName = Question.TABLE_NAME, daoClass = QuestionDAOImpl.class)
public class Question extends BaseModel {

    public static final String TABLE_NAME = "question";

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_QUESTION = "question";

    public static final String COLUMN_QUESTION_TYPE = "question_type_id";

    public static final String COLUMN_QUESTION_CATEGORY = "question_category_id";

    @DatabaseField(columnName = COLUMN_CODE, unique = false, canBeNull = false)
    private String code;

    @DatabaseField(columnName = COLUMN_QUESTION, canBeNull = false)
    private String question;

    @DatabaseField(columnName = COLUMN_QUESTION_CATEGORY, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private QuestionsCategory questionsCategory;

    public Question() {
    }

    public Question(QuestionDTO questionDTO) {
        super(questionDTO);
        this.setCode(questionDTO.getCode());
        if(questionDTO.getQuestion()!=null) this.setQuestion(questionDTO.getQuestion());
        if(questionDTO.getQuestionCategory()!=null) this.setQuestionsCategory(new QuestionsCategory(questionDTO.getQuestionCategory()));
    }

    public String getCode() {
        return code;
    }

    public String getQuestion() {
        return question;
    }

    public QuestionsCategory getQuestionsCategory() {
        return questionsCategory;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionsCategory(QuestionsCategory questionsCategory) {
        this.questionsCategory = questionsCategory;
    }
}
