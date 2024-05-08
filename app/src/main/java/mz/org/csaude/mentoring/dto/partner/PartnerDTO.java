package mz.org.csaude.mentoring.dto.partner;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.partner.Partner;
@Data
@NoArgsConstructor
public class PartnerDTO extends BaseEntityDTO {

    private String uuid;
    private String name;
    private String description;

    public PartnerDTO() {
    }

    public PartnerDTO(Partner partner) {
        this.setUuid(partner.getUuid());
        this.name = partner.getName();
        this.description = partner.getDescription();
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Partner getPartner() {
        Partner partner = new Partner();
        partner.setId(this.getId());
        partner.setUuid(this.getUuid());
        partner.setSyncStatus(this.getSyncSatus());
        partner.setDescription(this.getDescription());
        partner.setName(this.getName());
        return partner;
    }
}
