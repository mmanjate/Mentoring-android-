package mz.org.csaude.mentoring.service.career;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.ICareerTypeDAO;
import mz.org.csaude.mentoring.model.career.CareerType;
import mz.org.csaude.mentoring.model.user.User;

public class CareeTypeServiceImpl extends BaseServiceImpl<CareerType> implements CareeTypeService {

    ICareerTypeDAO careerTypeDAO;

    public CareeTypeServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public CareeTypeServiceImpl(Application application) {
        super(application);
    }
    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application,currentUser);
        this.careerTypeDAO = getDataBaseHelper().getCareerTypeDAO();
    }

    @Override
    public CareerType save(CareerType record) throws SQLException {
        this.careerTypeDAO.create(record);
        return record;
    }

    @Override
    public CareerType update(CareerType record) throws SQLException {
        this.careerTypeDAO.update(record);
        return record;
    }

    @Override
    public int delete(CareerType record) throws SQLException {
        return this.careerTypeDAO.delete(record);
    }

    @Override
    public List<CareerType> getAll() throws SQLException {
        return this.careerTypeDAO.queryForAll();
    }

    @Override
    public CareerType getById(int id) throws SQLException {
        return this.careerTypeDAO.queryForId(id);
    }
}
