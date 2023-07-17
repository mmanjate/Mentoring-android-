package mz.org.csaude.mentoring.service.ProgrammaticArea;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.programmaticArea.ProgrammaticAreaDAO;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.model.user.User;

public class ProgrammaticAreaServiceImpl extends BaseServiceImpl<ProgrammaticArea> implements ProgrammaticAreaService {

    ProgrammaticAreaDAO programmaticAreaDAO;

    public ProgrammaticAreaServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public ProgrammaticAreaServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.programmaticAreaDAO = getDataBaseHelper().getProgrammaticAreaDAO();
    }

    @Override
    public ProgrammaticArea save(ProgrammaticArea record) throws SQLException {
        this.programmaticAreaDAO.create(record);
        return record;
    }

    @Override
    public ProgrammaticArea update(ProgrammaticArea record) throws SQLException {
        this.programmaticAreaDAO.update(record);
        return record;
    }

    @Override
    public int delete(ProgrammaticArea record) throws SQLException {
        return this.programmaticAreaDAO.delete(record);
    }

    @Override
    public List<ProgrammaticArea> getAll() throws SQLException {
        return this.programmaticAreaDAO.queryForAll();
    }

    @Override
    public ProgrammaticArea getById(int id) throws SQLException {
        return this.programmaticAreaDAO.queryForId(id);
    }
}
