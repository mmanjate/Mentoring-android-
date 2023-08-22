package mz.org.csaude.mentoring.dto.location;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.location.Cabinet;

import java.io.Serializable;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
public class CabinetDTO implements Serializable {

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
        this.setUuid(cabinet.getUuid());
        this.setName(cabinet.getName());
    }

    public Cabinet getCabinet(){
        return new Cabinet(this);
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
