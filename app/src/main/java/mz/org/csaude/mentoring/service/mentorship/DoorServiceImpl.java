package mz.org.csaude.mentoring.service.mentorship;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.mentorship.DoorDAO;
import mz.org.csaude.mentoring.model.mentorship.Door;
import mz.org.csaude.mentoring.model.user.User;

public class DoorServiceImpl extends BaseServiceImpl<Door> implements DoorService {

    DoorDAO doorDAO;

    public DoorServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.doorDAO = getDataBaseHelper().getDoorDAO();
    }

    @Override
    public Door save(Door record) throws SQLException {
        this.doorDAO.create(record);
        return record;
    }

    @Override
    public Door update(Door record) throws SQLException {
        this.doorDAO.update(record);
        return record;
    }

    @Override
    public int delete(Door record) throws SQLException {
        return this.doorDAO.delete(record);
    }

    @Override
    public List<Door> getAll() throws SQLException {
        return this.doorDAO.queryForAll();
    }

    @Override
    public Door getById(int id) throws SQLException {
        return this.doorDAO.queryForId(id);
    }
}
