package mz.org.csaude.mentoring.model.setting;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.partner.Partner;

public class PartnerSetting extends BaseModel {

    private Partner partner;

    private Setting setting;

    public PartnerSetting() {
    }

    public Partner getPartner() {
        return partner;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}
