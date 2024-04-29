package mz.org.csaude.mentoring.service.question;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.question.QuestionCategoryDTO;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;

public interface QuestionsCategoryService extends BaseService<QuestionsCategory> {
    void saveOrUpdateQuestionCategories(List<QuestionCategoryDTO> questionCategoryDTOS) throws SQLException;
    QuestionsCategory saveOrUpdateQuestionCategory(QuestionCategoryDTO questionCategoryDTO) throws SQLException;

}
