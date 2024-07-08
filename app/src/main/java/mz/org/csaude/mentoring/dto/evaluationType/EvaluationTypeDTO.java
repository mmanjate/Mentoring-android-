package mz.org.csaude.mentoring.dto.evaluationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;

@Data
@NoArgsConstructor
public class EvaluationTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;

    public EvaluationTypeDTO(EvaluationType evaluationType) {
        super(evaluationType);
        this.setCode(evaluationType.getCode());
        this.setDescription(evaluationType.getDescription());
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
        evaluationType.setUuid(this.getUuid());
        evaluationType.setCreatedAt(this.getCreatedAt());
        evaluationType.setUpdatedAt(this.getUpdatedAt());
        evaluationType.setLifeCycleStatus(this.getLifeCycleStatus());
        evaluationType.setCode(this.getCode());
        evaluationType.setDescription(this.getDescription());
        return evaluationType;
    }
}
