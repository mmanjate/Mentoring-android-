package mz.org.csaude.mentoring.dto.mentorship;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;
@Data
@NoArgsConstructor
public class TimeOfDayDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public TimeOfDayDTO(TimeOfDay timeOfDay) {
        super(timeOfDay);
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
        timeOfDay.setCode(this.getCode());
        timeOfDay.setDescription(this.getDescription());
        timeOfDay.setCreatedAt(this.getCreatedAt());
        timeOfDay.setUpdatedAt(this.getUpdatedAt());
        return timeOfDay;
    }
}
