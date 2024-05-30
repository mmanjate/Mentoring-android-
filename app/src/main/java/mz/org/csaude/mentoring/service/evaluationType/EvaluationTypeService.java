package mz.org.csaude.mentoring.service.evaluationType;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.evaluationType.EvaluationTypeDTO;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;

public interface EvaluationTypeService extends BaseService<EvaluationType> {
    void saveOrUpdateEvaluationTypes(List<EvaluationTypeDTO> evaluationTypeDTOS) throws SQLException;
    EvaluationType saveOrUpdateEvaluationType(EvaluationTypeDTO evaluationType) throws SQLException;

    EvaluationType getByCode(String consulta) throws SQLException;

}
