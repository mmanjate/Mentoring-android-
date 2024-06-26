package mz.org.csaude.mentoring.dao.ronda;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;

public class RondaMentorDAOImpl extends MentoringBaseDaoImpl<RondaMentor, Integer> implements RondaMentorDAO {
    public RondaMentorDAOImpl(Class<RondaMentor> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaMentorDAOImpl(ConnectionSource connectionSource, Class<RondaMentor> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaMentorDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<RondaMentor> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<RondaMentor> getRondaMentors(Ronda ronda) throws SQLException {
        return queryForEq(RondaMentor.COLUMN_RONDA, ronda.getId());
    }
}
