package mz.org.csaude.mentoring.service.formQuestion;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.form.FormQuestionDTO;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;

public interface FormQuestionService extends BaseService<FormQuestion> {
    void saveOrUpdateFormQuestions(List<FormQuestionDTO> formQuestionDTOS) throws SQLException;
    FormQuestion saveOrUpdateFormQuestion(FormQuestionDTO formQuestionDTO) throws SQLException;

    void saveOrUpdate(List<FormQuestion> formQuestionDTOS) throws SQLException;
}
