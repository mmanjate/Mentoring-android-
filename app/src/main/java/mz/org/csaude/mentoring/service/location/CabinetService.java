package mz.org.csaude.mentoring.service.location;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.model.location.Cabinet;

public interface CabinetService extends BaseService<Cabinet> {

    void saveOrUpdateCabinets(List<CabinetDTO> cabinets) throws SQLException;
}
