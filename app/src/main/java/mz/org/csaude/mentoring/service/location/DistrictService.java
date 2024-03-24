package mz.org.csaude.mentoring.service.location;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;

public interface DistrictService extends BaseService<District> {
    List<District> getByProvince(Province selectedProvince) throws SQLException;
}
