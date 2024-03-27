package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.location.Province;

@NoArgsConstructor
public class ProvinceDTO implements Serializable {

    private String uuid;
    private String designation;

    public ProvinceDTO(Province province) {
        this.setUuid(province.getUuid());
        this.setDesignation(province.getDescription());
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
