package mz.org.csaude.mentoring.service.location;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;

public interface HealthFacilityService extends BaseService<HealthFacility> {

    void savedOrUpdatHealthFacilitys(List<HealthFacilityDTO> healthFacilityDTOs) throws SQLException;

    HealthFacility savedOrUpdatHealthFacility(HealthFacilityDTO healthFacilityDTO) throws SQLException;

    List<HealthFacility> getHealthFacilityByDistrict(District district) throws SQLException;
}
