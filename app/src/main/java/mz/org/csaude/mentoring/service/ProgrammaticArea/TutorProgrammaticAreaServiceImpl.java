package mz.org.csaude.mentoring.service.ProgrammaticArea;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.programmaticArea.TutorProgrammaticAreaDAO;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.user.User;

public class TutorProgrammaticAreaServiceImpl extends BaseServiceImpl<TutorProgrammaticArea> implements TutorProgrammaticAreaService{

    TutorProgrammaticAreaDAO tutorProgrammaticAreaDAO;

    public TutorProgrammaticAreaServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TutorProgrammaticAreaServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.tutorProgrammaticAreaDAO = getDataBaseHelper().getTutorProgrammaticAreaDAO();
    }

    @Override
    public TutorProgrammaticArea save(TutorProgrammaticArea record) throws SQLException {
        this.tutorProgrammaticAreaDAO.create(record);
        return record;
    }

    @Override
    public TutorProgrammaticArea update(TutorProgrammaticArea record) throws SQLException {
        this.tutorProgrammaticAreaDAO.update(record);
        return record;
    }

    @Override
    public int delete(TutorProgrammaticArea record) throws SQLException {
        return this.tutorProgrammaticAreaDAO.delete(record);
    }

    @Override
    public List<TutorProgrammaticArea> getAll() throws SQLException {
        return this.tutorProgrammaticAreaDAO.queryForAll();
    }

    @Override
    public TutorProgrammaticArea getById(int id) throws SQLException {
        return this.tutorProgrammaticAreaDAO.queryForId(id);
    }
}
