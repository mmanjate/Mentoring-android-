package mz.org.csaude.mentoring.dto.mentorship;



import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;


public class TimeOfDayDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public TimeOfDayDTO(TimeOfDay timeOfDay) {
        super(timeOfDay);
        this.setCode(timeOfDay.getCode());
        this.setDescription(timeOfDay.getDescription());
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
    public TimeOfDay getTimeOfDay() {
        TimeOfDay timeOfDay = new TimeOfDay();
        timeOfDay.setUuid(this.getUuid());
        timeOfDay.setCreatedAt(this.getCreatedAt());
        timeOfDay.setUpdatedAt(this.getUpdatedAt());
        timeOfDay.setLifeCycleStatus(this.getLifeCycleStatus());
        timeOfDay.setCode(this.getCode());
        timeOfDay.setDescription(this.getDescription());
        return timeOfDay;
    }
}
