package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;

public interface HealthFacilityDAO extends MentoringBaseDao<HealthFacility, Integer> {

    public boolean checkHealthFacilityExistance(final String uuid) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrict(District district) throws SQLException;
}
