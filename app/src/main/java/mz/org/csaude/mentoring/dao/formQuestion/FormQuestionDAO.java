package mz.org.csaude.mentoring.dao.formQuestion;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;

public interface FormQuestionDAO extends MentoringBaseDao<FormQuestion, Integer> {
    List<FormQuestion> getAllOfForm(Form form, String evaluationTipe, MentoringApplication application) throws SQLException;

}
