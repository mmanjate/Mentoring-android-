package mz.org.csaude.mentoring.dto.location;

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
        super(district);
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
        district.setUuid(this.getUuid());
        district.setDescription(this.getDescription());
        district.setCreatedAt(this.getCreatedAt());
        district.setUpdatedAt(this.getUpdatedAt());
        if(district.getProvince()!=null) {
            district.setProvince(this.getDistrict().getProvince());
        }
        return district;
    }
}
