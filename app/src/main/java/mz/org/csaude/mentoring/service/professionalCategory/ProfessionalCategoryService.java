package mz.org.csaude.mentoring.service.professionalCategory;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;

public interface ProfessionalCategoryService extends BaseService<ProfessionalCategory> {

    void saveOrUpdateProfessionalCategorys(List<ProfessionalCategoryDTO> professionalCategoryDTOS) throws SQLException;

    ProfessionalCategory saveOrUpdateProfessionalCategory(ProfessionalCategoryDTO ProfessionalCategory) throws SQLException;
}
