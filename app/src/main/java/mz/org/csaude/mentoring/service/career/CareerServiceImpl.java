package mz.org.csaude.mentoring.service.career;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.CareerDAO;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.user.User;

public class CareerServiceImpl extends BaseServiceImpl<Career> implements CareerService {

    CareerDAO careerDAO;

    public CareerServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public CareerServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application,currentUser);
        this.careerDAO = getDataBaseHelper().getCareerDAO();
    }

    @Override
    public Career save(Career record) throws SQLException {

        this.careerDAO.create(record);
        return record;
    }

    @Override
    public Career update(Career record) throws SQLException {
        this.careerDAO.update(record);
        return record;
    }

    @Override
    public int delete(Career record) throws SQLException {
        return this.careerDAO.delete(record);
    }

    @Override
    public List<Career> getAll() throws SQLException {
        return this.careerDAO.queryForAll();
    }

    @Override
    public Career getById(int id) throws SQLException {
        return this.careerDAO.queryForId(id);
    }
}
