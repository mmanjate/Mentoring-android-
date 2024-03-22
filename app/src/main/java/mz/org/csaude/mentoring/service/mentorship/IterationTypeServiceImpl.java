package mz.org.csaude.mentoring.service.mentorship;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.mentorship.IterationTypeDAO;
import mz.org.csaude.mentoring.model.mentorship.IterationType;
import mz.org.csaude.mentoring.model.user.User;

public class IterationTypeServiceImpl extends BaseServiceImpl<IterationType> implements IterationTypeService {

    IterationTypeDAO iterationTypeDAO;



    public IterationTypeServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.iterationTypeDAO = getDataBaseHelper().getIterationTypeDAO();
    }

    @Override
    public IterationType save(IterationType record) throws SQLException {
        this.iterationTypeDAO.create(record);
        return record;
    }

    @Override
    public IterationType update(IterationType record) throws SQLException {
        this.iterationTypeDAO.update(record);
        return record;
    }

    @Override
    public int delete(IterationType record) throws SQLException {
        return this.iterationTypeDAO.delete(record);
    }

    @Override
    public List<IterationType> getAll() throws SQLException {
        return this.iterationTypeDAO.queryForAll();
    }

    @Override
    public IterationType getById(int id) throws SQLException {
        return this.iterationTypeDAO.queryForId(id);
    }
}
