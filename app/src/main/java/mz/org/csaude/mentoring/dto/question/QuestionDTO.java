package mz.org.csaude.mentoring.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;
@Data
@NoArgsConstructor
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
    public Question getQuestionObj() {
        Question question = new Question();
        question.setId(this.getId());
        question.setUuid(this.getUuid());
        question.setSyncStatus(this.getSyncSatus());
        question.setCode(this.getCode());
        question.setQuestion(this.getQuestion());
        if(this.getQuestionCategory()!=null) {
            question.setQuestionsCategory(this.getQuestionCategory().getQuestionCategory());
        }
        return question;
    }
}
