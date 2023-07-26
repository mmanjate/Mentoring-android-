package mz.org.csaude.mentoring.dao.question;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.Question.QuestionType;

public class QuestionTypeDAOImpl extends BaseDaoImpl<QuestionType, Integer> implements QuestionTypeDAO {

    protected QuestionTypeDAOImpl(Class<QuestionType> dataClass) throws SQLException {
        super(dataClass);
    }

    protected QuestionTypeDAOImpl(ConnectionSource connectionSource, Class<QuestionType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected QuestionTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<QuestionType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
