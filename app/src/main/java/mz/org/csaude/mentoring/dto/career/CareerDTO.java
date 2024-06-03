package mz.org.csaude.mentoring.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.career.Career;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CareerDTO extends BaseEntityDTO {
    @JsonProperty(value = "position")
    private String position;
    @JsonProperty(value = "careerType")
    private CareerTypeDTO careerTypeDTO;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public CareerTypeDTO getCareerTypeDTO() {
        return careerTypeDTO;
    }

    public void setCareerTypeDTO(CareerTypeDTO careerTypeDTO) {
        this.careerTypeDTO = careerTypeDTO;
    }
    public Career getCareer() {
        Career career = new Career();
        career.setUuid(this.getUuid());
        career.setPosition(this.getPosition());
        career.setCreatedAt(this.getCreatedAt());
        career.setUpdatedAt(this.getUpdatedAt());
        if(this.getCareerTypeDTO()!=null) {
            career.setCareerType(this.getCareerTypeDTO().getCareerType());
        }
        return career;
    }
}
