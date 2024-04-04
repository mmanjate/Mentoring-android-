package mz.org.csaude.mentoring.dao.partner;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.partner.Partner;

public interface PartnerDao extends MentoringBaseDao<Partner, Integer> {
    Partner getMISAU() throws SQLException;

}
