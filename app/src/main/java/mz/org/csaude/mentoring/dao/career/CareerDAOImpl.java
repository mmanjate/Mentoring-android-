package mz.org.csaude.mentoring.dao.career;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.career.Career;

public class CareerDAOImpl extends BaseDaoImpl<Career, Integer> implements CareerDAO {

    protected CareerDAOImpl(Class<Career> dataClass) throws SQLException {
        super(dataClass);
    }

    protected CareerDAOImpl(ConnectionSource connectionSource, Class<Career> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected CareerDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Career> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
