package mz.org.csaude.mentoring.service.tutor;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.tutor.TutorDAO;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;

public class TutorServiceImpl extends BaseServiceImpl<Tutor> implements TutorService {

    TutorDAO tutorDAO;

    public TutorServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TutorServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.tutorDAO = getDataBaseHelper().getTutorDAO();
    }

    @Override
    public Tutor save(Tutor record) throws SQLException {
        this.tutorDAO.create(record);
        return record;
    }

    @Override
    public Tutor update(Tutor record) throws SQLException {
        tutorDAO.update(record);
        return record;
    }

    @Override
    public int delete(Tutor record) throws SQLException {
        return this.tutorDAO.delete(record);
    }

    @Override
    public List<Tutor> getAll() throws SQLException {
        return this.tutorDAO.queryForAll();
    }

    @Override
    public Tutor getById(int id) throws SQLException {
        return this.tutorDAO.queryForId(id);
    }
}
