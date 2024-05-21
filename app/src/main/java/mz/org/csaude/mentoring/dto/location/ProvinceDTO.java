package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.location.Province;

@Data
@NoArgsConstructor
public class ProvinceDTO extends BaseEntityDTO {

    private String uuid;
    private String designation;
    public ProvinceDTO(Province province) {
        super(province);
        this.setDesignation(province.getDescription());
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Province getProvince() {
        Province province = new Province();
        province.setId(this.getId());
        province.setUuid(this.getUuid());
        province.setSyncStatus(this.getSyncSatus());
        province.setDescription(this.getDesignation());
        province.setCreatedAt(this.getCreatedAt());
        province.setUpdatedAt(this.getUpdatedAt());
        return province;
    }
}
