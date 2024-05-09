package mz.org.csaude.mentoring.service.session;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.session.SessionStatusDTO;
import mz.org.csaude.mentoring.model.session.SessionStatus;

public interface SessionStatusService extends BaseService<SessionStatus> {
    void saveOrUpdateSessionStatuses(List<SessionStatusDTO> sessionStatusDTOS) throws SQLException;
    SessionStatus saveOrUpdateSessionStatus(SessionStatusDTO sessionStatusDTO) throws SQLException;
}
