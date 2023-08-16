package mz.org.csaude.mentoring.service.location;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.CabinetDTO;
import mz.org.csaude.mentoring.model.location.Cabinet;

import java.sql.SQLException;
import java.util.List;

public interface CabinetService extends BaseService<Cabinet> {

    void saveOrUpdateCabinets(List<CabinetDTO> cabinets) throws SQLException;
}
