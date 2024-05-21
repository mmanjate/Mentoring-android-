package mz.org.csaude.mentoring.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.evaluationType.EvaluationTypeDTO;
import mz.org.csaude.mentoring.dto.question.QuestionCategoryDTO;
import mz.org.csaude.mentoring.dto.question.QuestionDTO;
import mz.org.csaude.mentoring.dto.responseType.ResponseTypeDTO;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.question.Question;
@Data
@NoArgsConstructor
public class FormQuestionDTO extends BaseEntityDTO {
    private boolean mandatory;
    private Integer sequence;
    private Boolean applicable;
    private QuestionDTO question;
    private EvaluationTypeDTO evaluationType;
    private ResponseTypeDTO responseType;
    public FormQuestionDTO(FormQuestion formQuestion) {
        super(formQuestion);
        if(formQuestion.getQuestion()!=null) {
            this.setQuestion(new QuestionDTO(formQuestion.getQuestion()));
        }
        if(formQuestion.getEvaluationType()!=null) {
            this.setEvaluationType(new EvaluationTypeDTO(formQuestion.getEvaluationType()));
        }
        if(formQuestion.getResponseType()!=null) {
            this.setResponseType(new ResponseTypeDTO(formQuestion.getResponseType()));
        }
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getApplicable() {
        return applicable;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public EvaluationTypeDTO getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(EvaluationTypeDTO evaluationType) {
        this.evaluationType = evaluationType;
    }

    public ResponseTypeDTO getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseTypeDTO responseType) {
        this.responseType = responseType;
    }
    public FormQuestion getFormQuestion() {
        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setUuid(this.getUuid());
        formQuestion.setMandatory(this.isMandatory());
        formQuestion.setApplicable(this.getApplicable());
        formQuestion.setSequence(this.getSequence());
        if(this.getQuestion()!=null) {
            formQuestion.setQuestion(this.getQuestion().getQuestionObj());
        }
        if(this.getEvaluationType()!=null) {
            formQuestion.setEvaluationType(this.getEvaluationType().getEvaluationType());
        }
        if(this.getResponseType()!=null) {
            formQuestion.setResponseType(this.getResponseType().getResponseType());
        }
        return formQuestion;
    }
}
