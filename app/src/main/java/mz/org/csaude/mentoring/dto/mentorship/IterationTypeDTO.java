package mz.org.csaude.mentoring.dto.mentorship;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.mentorship.IterationType;
@Data
@NoArgsConstructor
public class IterationTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public IterationTypeDTO(IterationType iterationType) {
        super(iterationType);
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
    public IterationType getIterationType() {
        IterationType iterationType = new IterationType();
        iterationType.setUuid(this.getUuid());
        iterationType.setCode(this.getCode());
        iterationType.setDescription(this.getDescription());
        iterationType.setCreatedAt(this.getCreatedAt());
        iterationType.setUpdatedAt(this.getUpdatedAt());
        return iterationType;
    }
}
