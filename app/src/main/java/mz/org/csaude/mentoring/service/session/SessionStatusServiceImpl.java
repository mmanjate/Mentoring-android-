package mz.org.csaude.mentoring.service.session;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.session.SessionStatusDAO;
import mz.org.csaude.mentoring.dto.session.SessionStatusDTO;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.model.user.User;

public class SessionStatusServiceImpl extends BaseServiceImpl<SessionStatus> implements SessionStatusService {

    SessionStatusDAO sessionStatusDAO;


    public SessionStatusServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.sessionStatusDAO = getDataBaseHelper().getSessionStatusDAO();
    }

    @Override
    public SessionStatus save(SessionStatus record) throws SQLException {
        this.sessionStatusDAO.create(record);
        return record;
    }

    @Override
    public SessionStatus update(SessionStatus record) throws SQLException {
        this.sessionStatusDAO.create(record);
        return record;
    }

    @Override
    public int delete(SessionStatus record) throws SQLException {
        return this.sessionStatusDAO.delete(record);
    }

    @Override
    public List<SessionStatus> getAll() throws SQLException {
        return this.sessionStatusDAO.queryForAll();
    }

    @Override
    public SessionStatus getById(int id) throws SQLException {
        return this.sessionStatusDAO.queryForId(id);
    }

    @Override
    public void saveOrUpdateSessionStatuses(List<SessionStatusDTO> sessionStatusDTOS) throws SQLException {
        for (SessionStatusDTO sessionStatusDTO: sessionStatusDTOS) {
            this.saveOrUpdateSessionStatus(sessionStatusDTO);
        }

    }

    @Override
    public SessionStatus saveOrUpdateSessionStatus(SessionStatusDTO sessionStatusDTO) throws SQLException {
        SessionStatus ss = this.sessionStatusDAO.getByUuid(sessionStatusDTO.getUuid());
        SessionStatus sessionStatus = sessionStatusDTO.getSessionStatus();
        if(ss!=null) {
            sessionStatus.setId(ss.getId());
        }
        this.sessionStatusDAO.createOrUpdate(sessionStatus);
        return sessionStatus;
    }
}
