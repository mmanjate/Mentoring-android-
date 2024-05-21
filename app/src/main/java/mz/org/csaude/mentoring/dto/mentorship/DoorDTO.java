package mz.org.csaude.mentoring.dto.mentorship;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.mentorship.Door;
@Data
@NoArgsConstructor
public class DoorDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public DoorDTO(Door door) {
        super(door);
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
    public Door getDoor() {
        Door door = new Door();
        door.setCode(this.getCode());
        door.setUuid(this.getUuid());
        door.setDescription(this.getDescription());
        return door;
    }
}
