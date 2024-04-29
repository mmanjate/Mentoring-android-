package mz.org.csaude.mentoring.dto.question;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;

public class QuestionDTO extends BaseEntityDTO {
    private String code;
    private String question;
    private QuestionCategoryDTO questionCategory;
    public QuestionDTO(Question question) {
        super(question);
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
}
