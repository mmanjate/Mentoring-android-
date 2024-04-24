package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
public class RondaTypeDTO implements Serializable {
    private String uuid;
    private String code;
    private String description;

    public RondaTypeDTO(String code) {
        this.setCode(code);
    }

    public RondaTypeDTO(String uuid, String code, String description) {
        this.setUuid(uuid);
        this.setCode(code);
        this.setDescription(description);
    }

    public RondaTypeDTO(RondaType rondaType) {
        this.setUuid(rondaType.getUuid());
        this.setCode(rondaType.getCode());
        this.setDescription(rondaType.getDescription());
    }

    public RondaType getRondaType(){
        return new RondaType(this);
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
