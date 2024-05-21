package mz.org.csaude.mentoring.dto.evaluationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.question.QuestionType;

@Data
@NoArgsConstructor
public class EvaluationTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;

    public EvaluationTypeDTO(EvaluationType evaluationType) {
        super(evaluationType);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EvaluationType getEvaluationType() {
        EvaluationType evaluationType = new EvaluationType();
        evaluationType.setCode(this.getCode());
        evaluationType.setUuid(this.getUuid());
        evaluationType.setDescription(this.getDescription());
        evaluationType.setCreatedAt(this.getCreatedAt());
        evaluationType.setUpdatedAt(this.getUpdatedAt());
        return evaluationType;
    }
}
