package mz.org.csaude.mentoring.service.location;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.DistrictDTO;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface DistrictService extends BaseService<District> {

    void savedOrUpdateDistricts(List<DistrictDTO> districtDTOs) throws SQLException;
    District savedOrUpdateDistrict(District district) throws SQLException;

    List<District> getByProvince(Province selectedProvince) throws SQLException;

    List<District> getByProvinceAndMentor(Province province, Tutor currMentor) throws SQLException;
}
