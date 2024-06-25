package mz.org.csaude.mentoring.service.session;

import android.app.Application;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.model.session.Session;

public class SessionClosureServiceImpl extends BaseServiceImpl<Session> implements SessionClosureService {
    SessionDAO sessionDAO;

    public SessionClosureServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.sessionDAO = getDataBaseHelper().getSessionDAO();
    }

    @Override
    public Session save(Session record) throws SQLException {
        this.sessionDAO.update(record);
        return record;
    }

    @Override
    public Session update(Session record) throws SQLException {
        return null;
    }

    @Override
    public int delete(Session record) throws SQLException {
        return 0;
    }

    @Override
    public List<Session> getAll() throws SQLException {
        return null;
    }

    @Override
    public Session getById(int id) throws SQLException {
        return null;
    }

    @Override
    public Session endSession(Session record, Date endDate) {
        record.setEndDate(endDate);
        return record;
    }
}
