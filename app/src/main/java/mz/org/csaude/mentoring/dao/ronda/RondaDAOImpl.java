package mz.org.csaude.mentoring.dao.ronda;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.ronda.Ronda;

public class RondaDAOImpl extends BaseDaoImpl<Ronda, Integer> implements RondaDAO {
    public RondaDAOImpl(Class<Ronda> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaDAOImpl(ConnectionSource connectionSource, Class<Ronda> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Ronda> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
