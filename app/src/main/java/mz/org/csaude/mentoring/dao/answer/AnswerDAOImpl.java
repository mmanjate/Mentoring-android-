package mz.org.csaude.mentoring.dao.answer;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.answer.Answer;

public class AnswerDAOImpl extends BaseDaoImpl<Answer, Integer> implements AnswerDAO {

    protected AnswerDAOImpl(Class<Answer> dataClass) throws SQLException {
        super(dataClass);
    }

    protected AnswerDAOImpl(ConnectionSource connectionSource, Class<Answer> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected AnswerDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Answer> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
