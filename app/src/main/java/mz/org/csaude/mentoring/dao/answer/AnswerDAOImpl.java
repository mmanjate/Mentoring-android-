package mz.org.csaude.mentoring.dao.answer;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public class AnswerDAOImpl extends MentoringBaseDaoImpl<Answer, Integer> implements AnswerDAO {

    public AnswerDAOImpl(Class<Answer> dataClass) throws SQLException {
        super(dataClass);
    }

    public AnswerDAOImpl(ConnectionSource connectionSource, Class<Answer> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public AnswerDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Answer> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<Answer> queryForMentorship(Mentorship mentorship) throws SQLException {
        return queryBuilder().where().eq(Answer.COLUMN_MENTORSHIP, mentorship.getId()).query();
    }
}
