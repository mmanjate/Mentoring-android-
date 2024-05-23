package mz.org.csaude.mentoring.service.tutor;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.tutor.TutorTutoredDao;
import mz.org.csaude.mentoring.model.tutor.TutorTutored;

public class TutorTutoredServiceImpl extends BaseServiceImpl<TutorTutored> implements TutorTutoredService {

    private TutorTutoredDao tutorTutoredDao;
    public TutorTutoredServiceImpl(Application application) {
        super(application);
        try {
            this.tutorTutoredDao = dataBaseHelper.getTutorTutoredDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TutorTutored save(TutorTutored record) throws SQLException {
         this.tutorTutoredDao.create(record);
        return record;
    }

    @Override
    public TutorTutored update(TutorTutored record) throws SQLException {
        this.tutorTutoredDao.update(record);
        return record;
    }

    @Override
    public int delete(TutorTutored record) throws SQLException {
        return  this.tutorTutoredDao.delete(record);
    }

    @Override
    public List<TutorTutored> getAll() throws SQLException {
        return this.tutorTutoredDao.queryForAll();
    }

    @Override
    public TutorTutored getById(int id) throws SQLException {
        return this.tutorTutoredDao.queryForId(id);
    }
}
