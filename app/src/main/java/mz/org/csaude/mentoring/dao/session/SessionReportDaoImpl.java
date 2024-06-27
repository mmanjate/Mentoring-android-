package mz.org.csaude.mentoring.dao.session;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.session.SessionReport;

public class SessionReportDaoImpl extends MentoringBaseDaoImpl<SessionReport, Integer> implements SessionReportDao {

    public SessionReportDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, SessionReport.class);
    }

    public SessionReportDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<SessionReport> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<SessionReport> findBySessionId(int sessionId) throws SQLException {
        return queryBuilder().where().eq(SessionReport.COLUMN_SESSION_ID, sessionId).query();
    }

    @Override
    public List<SessionReport> findByTutoredId(int tutoredId) throws SQLException {
        return queryBuilder().where().eq(SessionReport.COLUMN_TUTORED_ID, tutoredId).query();
    }

    @Override
    public List<SessionReport> findByCategory(String category) throws SQLException {
        return queryBuilder().where().eq(SessionReport.COLUMN_CATEGORY, category).query();
    }
}
