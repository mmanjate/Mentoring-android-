package mz.org.csaude.mentoring.service.tutored;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.model.user.User;

public class TutoredServiceImpl extends BaseServiceImpl<Tutored> implements TutoredService{

    TutoredDao tutoredDao;

    public TutoredServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TutoredServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.tutoredDao = getDataBaseHelper().getTutoredDao();
    }

    public Tutored save(Tutored tutored) throws SQLException {
        this.tutoredDao.create(tutored);
        return tutored;

    }

    @Override
    public Tutored update(Tutored record) throws SQLException {
        this.tutoredDao.update(record);
        return record;
    }

    @Override
    public int delete(Tutored record) throws SQLException {
        return this.tutoredDao.delete(record);
    }

    @Override
    public List<Tutored> getAll() throws SQLException {
        return this.tutoredDao.queryForAll();
    }

    @Override
    public Tutored getById(int id) throws SQLException {
        return this.tutoredDao.queryForId(id);
    }
}
