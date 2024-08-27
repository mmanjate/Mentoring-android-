package mz.org.csaude.mentoring.dto.career;



import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.career.CareerType;

/**
 * @author Jose Julai Ritsure
 */


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
        super(careerType);
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
        careerType.setUuid(this.getUuid());
        careerType.setCreatedAt(this.getCreatedAt());
        careerType.setUpdatedAt(this.getUpdatedAt());
        careerType.setLifeCycleStatus(this.getLifeCycleStatus());
        careerType.setCode(this.getCode());
        careerType.setDescription(this.getDescription());
        return careerType;
    }
}
