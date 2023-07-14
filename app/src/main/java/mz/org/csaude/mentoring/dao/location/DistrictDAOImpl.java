package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.location.District;

public class DistrictDAOImpl extends BaseDaoImpl<District, Integer> implements DistrictDAO {

    protected DistrictDAOImpl(Class<District> dataClass) throws SQLException {
        super(dataClass);
    }

    protected DistrictDAOImpl(ConnectionSource connectionSource, Class<District> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected DistrictDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<District> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
