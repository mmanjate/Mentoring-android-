package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.location.Province;

public class ProvinceDAOImpl extends BaseDaoImpl<Province, Integer> implements ProvinceDAO {

    protected ProvinceDAOImpl(Class<Province> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ProvinceDAOImpl(ConnectionSource connectionSource, Class<Province> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ProvinceDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Province> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
