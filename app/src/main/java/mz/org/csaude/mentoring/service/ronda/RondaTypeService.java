package mz.org.csaude.mentoring.service.ronda;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.location.RondaTypeDTO;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

public interface RondaTypeService extends BaseService<RondaType> {
    public void saveOrUpdateRondaTypes(List<RondaTypeDTO> rondaTypeDTOS) throws SQLException;
    RondaType saveOrUpdateRondaType(RondaType rondaType) throws SQLException;
    RondaType getRondaTypeByCode(String code) throws SQLException;
    List<RondaType> doSearch(long offset, long limit);
}
