package mz.org.csaude.mentoring.dao.mentorship;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.mentorship.IterationType;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class IterationTypeDAOImpl extends MentoringBaseDaoImpl<IterationType, Integer> implements IterationTypeDAO {

    public IterationTypeDAOImpl(Class<IterationType> dataClass) throws SQLException {
        super(dataClass);
    }

    public IterationTypeDAOImpl(ConnectionSource connectionSource, Class<IterationType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public IterationTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<IterationType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public IterationType getByCode(String code) throws SQLException {
        return queryBuilder().where().eq(IterationType.COLUMN_CODE, code).queryForFirst();
    }
}
