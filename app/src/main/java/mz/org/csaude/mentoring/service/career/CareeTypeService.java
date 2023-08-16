package mz.org.csaude.mentoring.service.career;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.career.CareerTypeDTO;
import mz.org.csaude.mentoring.model.career.CareerType;

import java.sql.SQLException;
import java.util.List;

public interface CareeTypeService extends BaseService<CareerType> {

    void savedOrUpdateCareerTypes(List<CareerTypeDTO> careerTypeDTOS) throws SQLException;
}
