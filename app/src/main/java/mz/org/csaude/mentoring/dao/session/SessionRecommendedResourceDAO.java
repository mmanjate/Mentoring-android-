package mz.org.csaude.mentoring.dao.session;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;

public interface SessionRecommendedResourceDAO extends MentoringBaseDao<SessionRecommendedResource, Integer> {
    // Add custom methods for specific queries if needed
    List<SessionRecommendedResource> findBySessionId(int sessionId) throws SQLException;

    List<SessionRecommendedResource> findByTutorId(int tutorId) throws SQLException;

    List<SessionRecommendedResource> findByTutoredId(int tutoredId) throws SQLException;
}