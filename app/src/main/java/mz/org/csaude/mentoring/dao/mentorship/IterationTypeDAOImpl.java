package mz.org.csaude.mentoring.dao.mentorship;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.mentorship.IterationType;

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
}
