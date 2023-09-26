package mz.org.csaude.mentoring.dto.partner;


import java.io.Serializable;

import mz.org.csaude.mentoring.model.partner.Partner;

public class PartnerDTO implements Serializable {

    private String name;

    private String description;

    public PartnerDTO() {
    }

    public PartnerDTO(Partner partner) {
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
}
