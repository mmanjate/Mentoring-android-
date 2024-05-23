package mz.org.csaude.mentoring.dao.tutored;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public interface TutoredDao extends MentoringBaseDao<Tutored, Integer> {

    public boolean checkTutoredExistance(final String uuid) throws SQLException;

    List<Tutored> getAllOfHealthFacility(HealthFacility healthFacility, MentoringApplication application) throws SQLException;

    List<Tutored> getAllNotSynced() throws SQLException;

}
