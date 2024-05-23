package mz.org.csaude.mentoring.dao.partner;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.partner.Partner;

public interface PartnerDao extends MentoringBaseDao<Partner, Integer> {
    Partner getMISAU() throws SQLException;

    List<Partner> getNotMISAU() throws SQLException;

}
