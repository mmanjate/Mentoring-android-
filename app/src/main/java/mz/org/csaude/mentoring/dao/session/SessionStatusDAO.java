package mz.org.csaude.mentoring.dao.session;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.session.SessionStatus;

public interface SessionStatusDAO extends MentoringBaseDao<SessionStatus, Integer> {
    SessionStatus getByCode(String code) throws SQLException;
}
