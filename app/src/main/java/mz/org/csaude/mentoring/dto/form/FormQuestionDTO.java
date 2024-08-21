package mz.org.csaude.mentoring.dto.form;



import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.evaluationType.EvaluationTypeDTO;
import mz.org.csaude.mentoring.dto.question.QuestionDTO;
import mz.org.csaude.mentoring.dto.responseType.ResponseTypeDTO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.responseType.ResponseType;



public class FormQuestionDTO extends BaseEntityDTO {
    private Integer sequence;
    private QuestionDTO question;
    private EvaluationTypeDTO evaluationType;
    private ResponseTypeDTO responseType;
    private FormDTO form;
    private String formUuid;
    public FormQuestionDTO() {

    }
    public FormQuestionDTO(FormQuestion formQuestion) {
        super(formQuestion);
        this.setSequence(formQuestion.getSequence());
        if(formQuestion.getQuestion()!=null) {
            this.setQuestion(new QuestionDTO(formQuestion.getQuestion()));
        }
        if(formQuestion.getEvaluationType()!=null) {
            this.setEvaluationType(new EvaluationTypeDTO(formQuestion.getEvaluationType()));
        }
        if(formQuestion.getResponseType()!=null) {
            this.setResponseType(new ResponseTypeDTO(formQuestion.getResponseType()));
        }
        if(formQuestion.getForm()!=null) {
            this.setFormUuid(formQuestion.getForm().getUuid());
            this.setForm(new FormDTO(formQuestion.getForm()));
        }
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public FormDTO getForm() {
        return form;
    }

    public void setForm(FormDTO formDTO) {
        this.form = formDTO;
    }

    public String getFormUuid() {
        return formUuid;
    }

    public void setFormUuid(String formUuid) {
        this.formUuid = formUuid;
    }

    public void setResponseType(ResponseTypeDTO responseType) {
        this.responseType = responseType;
    }
    public FormQuestion getFormQuestion() {
        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setUuid(this.getUuid());
        formQuestion.setCreatedAt(this.getCreatedAt());
        formQuestion.setUpdatedAt(this.getUpdatedAt());
        formQuestion.setLifeCycleStatus(this.getLifeCycleStatus());
        formQuestion.setSequence(this.getSequence());
        if(this.getQuestion()!=null) {
            formQuestion.setQuestion(new Question(this.getQuestion()));
        }
        if(this.getEvaluationType()!=null) {
            formQuestion.setEvaluationType(new EvaluationType(this.getEvaluationType()));
        }
        if(this.getResponseType()!=null) {
            formQuestion.setResponseType(new ResponseType(this.getResponseType()));
        }
        if(this.getForm()!=null) {
            formQuestion.setForm(new Form(this.getForm()));
        }
        return formQuestion;
    }
}
