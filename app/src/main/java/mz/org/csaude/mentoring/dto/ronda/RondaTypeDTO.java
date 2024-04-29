package mz.org.csaude.mentoring.dto.ronda;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
@Data
@NoArgsConstructor
public class RondaTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public RondaTypeDTO(RondaType rondaType) {
        super(rondaType);
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
}
