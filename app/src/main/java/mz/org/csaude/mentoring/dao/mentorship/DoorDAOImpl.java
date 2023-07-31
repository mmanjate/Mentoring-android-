package mz.org.csaude.mentoring.dao.mentorship;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.mentorship.Door;

public class DoorDAOImpl extends BaseDaoImpl<Door, Integer> implements DoorDAO {

    protected DoorDAOImpl(Class<Door> dataClass) throws SQLException {
        super(dataClass);
    }

    protected DoorDAOImpl(ConnectionSource connectionSource, Class<Door> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected DoorDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Door> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
