package mz.org.csaude.mentoring.service.tutor;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.tutor.TutorLocationDAO;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutor.TutorLocation;
import mz.org.csaude.mentoring.model.user.User;

public class TutorLocationServiceImpl extends BaseServiceImpl<TutorLocation> implements TutorLocationService {

    TutorLocationDAO tutorLocationDAO;

    public TutorLocationServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TutorLocationServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.tutorLocationDAO = getDataBaseHelper().getTutorLocationDAO();
    }

    @Override
    public TutorLocation save(TutorLocation record) throws SQLException {
        this.tutorLocationDAO.create(record);
        return record;
    }

    @Override
    public TutorLocation update(TutorLocation record) throws SQLException {
        this.tutorLocationDAO.update(record);
        return record;
    }

    @Override
    public int delete(TutorLocation record) throws SQLException {
        return this.tutorLocationDAO.delete(record);
    }

    @Override
    public List<TutorLocation> getAll() throws SQLException {
        return this.tutorLocationDAO.queryForAll()
                ;
    }

    @Override
    public TutorLocation getById(int id) throws SQLException {
        return this.tutorLocationDAO.queryForId(id);
    }
}
