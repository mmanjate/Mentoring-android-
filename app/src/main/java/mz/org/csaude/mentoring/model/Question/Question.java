package mz.org.csaude.mentoring.model.Question;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Question extends BaseModel {

    private String code;

    private String question;

    private QuestionType questionType;

    private QuestionsCategory questionsCategory;

    public Question() {
    }

    public String getCode() {
        return code;
    }

    public String getQuestion() {
        return question;
    }

    public QuestionType getQuestionType() {
        return questionType;
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

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setQuestionsCategory(QuestionsCategory questionsCategory) {
        this.questionsCategory = questionsCategory;
    }
}
