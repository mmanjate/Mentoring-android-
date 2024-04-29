package mz.org.csaude.mentoring.service.responseType;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.evaluationType.EvaluationTypeDTO;
import mz.org.csaude.mentoring.dto.responseType.ResponseTypeDTO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.responseType.ResponseType;

public interface ResponseTypeService extends BaseService<ResponseType> {
    void saveOrUpdateResponseTypes(List<ResponseTypeDTO> responseTypeDTOS) throws SQLException;
    ResponseType saveOrUpdateResponseType(ResponseTypeDTO responseTypeDTO) throws SQLException;
}
