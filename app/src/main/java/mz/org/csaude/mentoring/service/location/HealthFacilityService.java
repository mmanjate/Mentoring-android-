package mz.org.csaude.mentoring.service.location;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface HealthFacilityService extends BaseService<HealthFacility> {

    void savedOrUpdatHealthFacilitys(List<HealthFacility> healthFacilityDTOs) throws SQLException;

    HealthFacility savedOrUpdatHealthFacility(HealthFacility healthFacility) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrict(District district) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrictAndMentor(District district, Tutor mentor) throws SQLException;
}
