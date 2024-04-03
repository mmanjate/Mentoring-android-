package mz.org.csaude.mentoring.service.partner;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.partner.Partner;

public interface PartnerService extends BaseService<Partner> {
    Partner savedOrUpdatePartner(Partner partner) throws SQLException;

    void saveAll(List<Partner> partners) throws SQLException;

    Partner getMISAU() throws SQLException;

}
