package mz.org.csaude.mentoring.dao.rondatype;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.rondatype.RondaType;

public class RondaTypeDAOImpl extends BaseDaoImpl<RondaType, Integer> implements RondaTypeDAO {
    public RondaTypeDAOImpl(Class<RondaType> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaTypeDAOImpl(ConnectionSource connectionSource, Class<RondaType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<RondaType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
