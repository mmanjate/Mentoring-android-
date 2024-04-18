package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.location.District;

@NoArgsConstructor
public class DistrictDTO  extends BaseEntityDTO {

    private String uuid;
    private String description;
    private ProvinceDTO provinceDTO;

    public DistrictDTO(District district) {
        super(district);
        this.setDescription(district.getDescription());
        if (district.getProvince() != null)  this.setProvinceDTO(new ProvinceDTO(district.getProvince()));
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProvinceDTO getProvinceDTO() {
        return provinceDTO;
    }

    public void setProvinceDTO(ProvinceDTO provinceDTO) {
        this.provinceDTO = provinceDTO;
    }
}
