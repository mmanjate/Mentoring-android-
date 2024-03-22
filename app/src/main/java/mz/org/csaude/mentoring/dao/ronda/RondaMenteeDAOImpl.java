package mz.org.csaude.mentoring.dao.ronda;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.ronda.RondaMentee;

public class RondaMenteeDAOImpl extends BaseDaoImpl<RondaMentee, Integer> implements RondaMenteeDAO {
    public RondaMenteeDAOImpl(Class<RondaMentee> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaMenteeDAOImpl(ConnectionSource connectionSource, Class<RondaMentee> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaMenteeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<RondaMentee> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
