package mz.org.csaude.mentoring.dto.question;


import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;


public class QuestionDTO extends BaseEntityDTO {
    private String code;
    private String question;
    private QuestionCategoryDTO questionCategory;

    public QuestionDTO() {
        super();
    }
    public QuestionDTO(Question question) {
        super(question);
        this.setCode(question.getCode());
        this.setQuestion(question.getQuestion());
        if(question.getQuestionsCategory()!=null) {
            this.setQuestionCategory(new QuestionCategoryDTO(question.getQuestionsCategory()));
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public QuestionCategoryDTO getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategoryDTO questionCategory) {
        this.questionCategory = questionCategory;
    }
    public Question getQuestionObj() {
        Question question = new Question();
        question.setQuestion(this.getQuestion());
        question.setCode(this.getCode());
        question.setLifeCycleStatus(this.getLifeCycleStatus());
        question.setUuid(this.getUuid());
        question.setCreatedAt(this.getCreatedAt());
        question.setUpdatedAt(this.getUpdatedAt());
        if (this.getQuestionCategory()!=null) question.setQuestionsCategory(new QuestionsCategory(this.getQuestionCategory()));
        return question;
    }
}
