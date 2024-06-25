package mz.org.csaude.mentoring.dao.session;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.session.SessionReport;

public interface SessionReportDao extends MentoringBaseDao<SessionReport, Integer> {
    // Add custom methods for specific queries if needed
    List<SessionReport> findBySessionId(int sessionId) throws SQLException;
    List<SessionReport> findByTutoredId(int tutoredId) throws SQLException;
    List<SessionReport> findByCategory(String category) throws SQLException;
}