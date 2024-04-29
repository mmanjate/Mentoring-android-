package mz.org.csaude.mentoring.service.mentorship;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.mentorship.IterationTypeDTO;
import mz.org.csaude.mentoring.model.mentorship.IterationType;

public interface IterationTypeService extends BaseService<IterationType> {
    void saveOrUpdateIterationTypes(List<IterationTypeDTO> iterationTypeDTOS) throws SQLException;
    IterationType saveOrUpdateIterationType(IterationTypeDTO iterationTypeDTO) throws SQLException;
}
