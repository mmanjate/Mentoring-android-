package mz.org.csaude.mentoring.dao.session;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.session.SessionStatus;

public class SessionStatusDAOImpl extends BaseDaoImpl<SessionStatus, Integer> implements SessionStatusDAO{

    public SessionStatusDAOImpl(Class<SessionStatus> dataClass) throws SQLException {
        super(dataClass);
    }

    public SessionStatusDAOImpl(ConnectionSource connectionSource, Class<SessionStatus> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SessionStatusDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<SessionStatus> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
