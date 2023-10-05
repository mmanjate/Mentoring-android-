package mz.org.csaude.mentoring.service.partner;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.partner.Partner;

public interface PartnerService extends BaseService<Partner> {
    Partner savedOrUpdatePartner(Partner partner) throws SQLException;
}
