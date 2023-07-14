package mz.org.csaude.mentoring.service.question;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.question.QuestionsCategoryDAO;
import mz.org.csaude.mentoring.model.Question.QuestionsCategory;
import mz.org.csaude.mentoring.model.user.User;

public class QuestionsCategoryServiceImpl extends BaseServiceImpl<QuestionsCategory>
implements QuestionsCategoryService{

    QuestionsCategoryDAO questionsCategoryDAO;

    public QuestionsCategoryServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public QuestionsCategoryServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.questionsCategoryDAO = getDataBaseHelper().getQuestionsCategoryDAO();
    }
    @Override
    public QuestionsCategory save(QuestionsCategory record) throws SQLException {
        this.questionsCategoryDAO.create(record);
        return record;
    }

    @Override
    public QuestionsCategory update(QuestionsCategory record) throws SQLException {
        this.questionsCategoryDAO.update(record);
        return record;
    }

    @Override
    public int delete(QuestionsCategory record) throws SQLException {
        return this.questionsCategoryDAO.delete(record);
    }

    @Override
    public List<QuestionsCategory> getAll() throws SQLException {
        return this.questionsCategoryDAO.queryForAll();
    }

    @Override
    public QuestionsCategory getById(int id) throws SQLException {
        return this.questionsCategoryDAO.queryForId(id);
    }
}
