package mz.org.csaude.mentoring.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.career.Career;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerDTO {

    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty(value = "position")
    private String position;

    @JsonProperty(value = "careerType")
    private CareerTypeDTO careerTypeDTO;

    public Career getCareer() {
        return this.getCareer();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
}
