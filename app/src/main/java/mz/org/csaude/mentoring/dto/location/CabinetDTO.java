package mz.org.csaude.mentoring.dto.location;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.location.Cabinet;

import java.io.Serializable;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
public class CabinetDTO extends BaseEntityDTO {

    private String uuid;

    private String name;

    public CabinetDTO(String name) {
        this.setName(name);
    }

    public CabinetDTO(String uuid, String name) {
        this.setUuid(uuid);
        this.setName(name);
    }

    public CabinetDTO(Cabinet cabinet) {
        super(cabinet);
        this.setUuid(cabinet.getUuid());
        this.setName(cabinet.getName());
    }

    public Cabinet getCabinet(){
        Cabinet cabinet = new Cabinet();
        cabinet.setUuid(this.getUuid());
        cabinet.setName(this.getName());
        cabinet.setCreatedAt(this.getCreatedAt());
        cabinet.setUpdatedAt(this.getUpdatedAt());
        return cabinet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
