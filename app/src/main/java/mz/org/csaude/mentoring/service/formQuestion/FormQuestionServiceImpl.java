package mz.org.csaude.mentoring.service.formQuestion;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.formQuestion.FormQuestionDAO;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.user.User;

public class FormQuestionServiceImpl extends BaseServiceImpl<FormQuestion> implements FormQuestionService {

    FormQuestionDAO formQuestionDAO;


    public FormQuestionServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.formQuestionDAO = getDataBaseHelper().getFormQuestionDAO();
    }
    @Override
    public FormQuestion save(FormQuestion record) throws SQLException {
        this.formQuestionDAO.create(record);
        return record;
    }

    @Override
    public FormQuestion update(FormQuestion record) throws SQLException {
        this.formQuestionDAO.update(record);
        return record;
    }

    @Override
    public int delete(FormQuestion record) throws SQLException {
        return this.formQuestionDAO.delete(record);
    }

    @Override
    public List<FormQuestion> getAll() throws SQLException {
        return this.formQuestionDAO.queryForAll();
    }

    @Override
    public FormQuestion getById(int id) throws SQLException {
        return this.formQuestionDAO.queryForId(id);
    }
}
