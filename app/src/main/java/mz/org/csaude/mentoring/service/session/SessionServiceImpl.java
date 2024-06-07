package mz.org.csaude.mentoring.service.session;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class SessionServiceImpl extends BaseServiceImpl<Session> implements SessionService{

    SessionDAO sessionDAO;


    public SessionServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.sessionDAO = getDataBaseHelper().getSessionDAO();
    }

    @Override
    public Session save(Session record) throws SQLException {
        this.sessionDAO.create(record);
        return record;
    }

    @Override
    public Session update(Session record) throws SQLException {
        this.sessionDAO.update(record);
        return record;
    }

    @Override
    public int delete(Session record) throws SQLException {
        return this.sessionDAO.delete(record);
    }

    @Override
    public List<Session> getAll() throws SQLException {
        return this.sessionDAO.queryForAll();
    }

    @Override
    public Session getById(int id) throws SQLException {
        return this.sessionDAO.queryForId(id);
    }

    @Override
    public List<Session> getAllOfRondaAndMentee(Ronda currRonda, Tutored selectedMentee, long offset, long limit) {
        return this.sessionDAO.queryForAllOfRondaAndMentee(currRonda, selectedMentee, getApplication());
    }
}
