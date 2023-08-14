package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.location.Cabinet;

public class CabinetDAOImpl extends BaseDaoImpl<Cabinet, Integer> implements CabinetDAO {

    public CabinetDAOImpl(Class<Cabinet> dataClass) throws SQLException {
        super(dataClass);
    }

    public CabinetDAOImpl(ConnectionSource connectionSource, Class<Cabinet> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public CabinetDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Cabinet> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
