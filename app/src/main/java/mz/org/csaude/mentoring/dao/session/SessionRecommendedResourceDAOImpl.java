package mz.org.csaude.mentoring.dao.session;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;

public class SessionRecommendedResourceDAOImpl extends MentoringBaseDaoImpl<SessionRecommendedResource, Integer> implements SessionRecommendedResourceDAO {
    public SessionRecommendedResourceDAOImpl(Class dataClass) throws SQLException {
        super(dataClass);
    }

    public SessionRecommendedResourceDAOImpl(ConnectionSource connectionSource, Class dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SessionRecommendedResourceDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<SessionRecommendedResource> findBySessionId(int sessionId) throws SQLException {
        return queryBuilder().where().eq(SessionRecommendedResource.COLUMN_SESSION_ID, sessionId).query();
    }

    @Override
    public List<SessionRecommendedResource> findByTutorId(int tutorId) throws SQLException {
        return queryBuilder().where().eq(SessionRecommendedResource.COLUMN_TUTOR_ID, tutorId).query();
    }

    @Override
    public List<SessionRecommendedResource> findByTutoredId(int tutoredId) throws SQLException {
        return queryBuilder().where().eq(SessionRecommendedResource.COLUMN_TUTORED_ID, tutoredId).query();
    }
}
