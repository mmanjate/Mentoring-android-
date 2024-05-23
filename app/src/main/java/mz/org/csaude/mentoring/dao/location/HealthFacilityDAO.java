package mz.org.csaude.mentoring.dao.location;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface HealthFacilityDAO extends MentoringBaseDao<HealthFacility, Integer> {

    public boolean checkHealthFacilityExistance(final String uuid) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrict(District district) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrictAndMentor(District district, Tutor mentor) throws SQLException;

}
