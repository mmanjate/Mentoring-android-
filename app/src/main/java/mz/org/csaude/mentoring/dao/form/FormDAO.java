package mz.org.csaude.mentoring.dao.form;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface FormDAO extends MentoringBaseDao<Form, Integer> {
    List<Form> getAllOfTutor(Tutor tutor, Application application) throws SQLException;
    List<Form> getAllNotSynced() throws SQLException;
    List<Form> getAllSynced(Application application) throws SQLException;
}
