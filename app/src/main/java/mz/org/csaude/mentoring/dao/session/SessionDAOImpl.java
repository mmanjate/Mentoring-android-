package mz.org.csaude.mentoring.dao.session;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.session.Session;

public class SessionDAOImpl extends BaseDaoImpl<Session, Integer> {

    protected SessionDAOImpl(Class<Session> dataClass) throws SQLException {
        super(dataClass);
    }

    protected SessionDAOImpl(ConnectionSource connectionSource, Class<Session> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected SessionDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Session> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
