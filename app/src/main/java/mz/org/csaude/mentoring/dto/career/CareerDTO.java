package mz.org.csaude.mentoring.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;





import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;

/**
 * @author Jose Julai Ritsure
 */



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

    public CareerDTO() {
    }

    public CareerDTO(Career career) {
        super(career);
        this.setPosition(career.getPosition());
        if(career.getCareerType()!=null) this.setCareerTypeDTO(new CareerTypeDTO(career.getCareerType()));
    }

    public void setCareerTypeDTO(CareerTypeDTO careerTypeDTO) {
        this.careerTypeDTO = careerTypeDTO;
    }
    public Career getCareer() {
        Career career = new Career();
        career.setUuid(this.getUuid());
        career.setCreatedAt(this.getCreatedAt());
        career.setUpdatedAt(this.getUpdatedAt());
        career.setLifeCycleStatus(this.getLifeCycleStatus());
        career.setPosition(this.getPosition());
        if(this.getCareerTypeDTO()!=null) career.setCareerType(new CareerType(this.getCareerTypeDTO()));
        return career;
    }
}
