package mz.org.csaude.mentoring.dto.location;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.location.District;
@Data
@NoArgsConstructor
public class DistrictDTO extends BaseEntityDTO {

    private String description;
    private ProvinceDTO provinceDTO;

    public DistrictDTO(District district) {
        this.setUuid(district.getUuid());
        this.setDescription(district.getDescription());
        if (district.getProvince() != null)  this.setProvinceDTO(new ProvinceDTO(district.getProvince()));
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
    public District getDistrict() {
        District district = new District();
        district.setId(this.getId());
        district.setUuid(this.getUuid());
        district.setSyncStatus(this.getSyncSatus());
        district.setDescription(this.getDescription());
        if(district.getProvince()!=null) {
            district.setProvince(this.getDistrict().getProvince());
        }
        return district;
    }
}
