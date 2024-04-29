package mz.org.csaude.mentoring.dto.career;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.career.CareerType;

import java.io.Serializable;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
public class CareerTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;

    public CareerTypeDTO(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public CareerTypeDTO(String uuid, String code, String description) {
        this.setUuid(uuid);
        this.code = code;
        this.description = description;
    }

    public CareerTypeDTO(CareerType careerType) {
        this.setUuid(careerType.getUuid());
        this.setCode(careerType.getCode());
        this.setDescription(careerType.getDescription());
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
    public CareerType getCareerType() {
        CareerType careerType = new CareerType();
        careerType.setId(this.getId());
        careerType.setUuid(this.getUuid());
        careerType.setCode(this.getCode());
        careerType.setDescription(this.getDescription());
        careerType.setSyncStatus(this.getSyncSatus());
        return careerType;
    }
}
