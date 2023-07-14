package mz.org.csaude.mentoring.dao.question;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.Question.Question;

public class QuestionDAOImpl extends BaseDaoImpl<Question, Integer> implements QuestionDAO {


    protected QuestionDAOImpl(Class<Question> dataClass) throws SQLException {
        super(dataClass);
    }

    protected QuestionDAOImpl(ConnectionSource connectionSource, Class<Question> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected QuestionDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Question> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
