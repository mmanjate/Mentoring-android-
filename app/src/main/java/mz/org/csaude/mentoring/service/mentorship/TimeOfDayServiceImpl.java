package mz.org.csaude.mentoring.service.mentorship;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.mentorship.TimeOfDayDAO;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;
import mz.org.csaude.mentoring.model.user.User;

public class TimeOfDayServiceImpl extends BaseServiceImpl<TimeOfDay> implements TimeOfDayService {

    TimeOfDayDAO timeOfDayDAO;

    public TimeOfDayServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TimeOfDayServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.timeOfDayDAO = getDataBaseHelper().getTimeOfDayDAO();
    }

    @Override
    public TimeOfDay save(TimeOfDay record) throws SQLException {
        this.timeOfDayDAO.create(record);
        return record;
    }

    @Override
    public TimeOfDay update(TimeOfDay record) throws SQLException {
        this.timeOfDayDAO.update(record);
        return record;
    }

    @Override
    public int delete(TimeOfDay record) throws SQLException {
        return this.timeOfDayDAO.delete(record);
    }

    @Override
    public List<TimeOfDay> getAll() throws SQLException {
        return this.timeOfDayDAO.queryForAll();
    }

    @Override
    public TimeOfDay getById(int id) throws SQLException {
        return this.timeOfDayDAO.queryForId(id);
    }
}
