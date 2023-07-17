package mz.org.csaude.mentoring.service.question;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.question.QuestionDAO;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.user.User;

public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

    QuestionDAO questionDAO;

    public QuestionServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public QuestionServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.questionDAO = getDataBaseHelper().getQuestionDAO();
    }

    @Override
    public Question save(Question record) throws SQLException {
        this.questionDAO.create(record);
        return record;
    }

    @Override
    public Question update(Question record) throws SQLException {
        this.questionDAO.update(record);
        return record;
    }

    @Override
    public int delete(Question record) throws SQLException {
        return this.questionDAO.delete(record);
    }

    @Override
    public List<Question> getAll() throws SQLException {
        return this.questionDAO.queryForAll();
    }

    @Override
    public Question getById(int id) throws SQLException {
        return this.questionDAO.queryForId(id);
    }
}
