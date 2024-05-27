package mz.org.csaude.mentoring.service.career;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.CareerTypeDAO;
import mz.org.csaude.mentoring.model.career.CareerType;

public class CareerTypeServiceImpl extends BaseServiceImpl<CareerType> implements CareerTypeService {

    CareerTypeDAO careerTypeDAO;

    public CareerTypeServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
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

    @Override
    public CareerType savedOrUpdateCareerTypes(CareerType careerType) throws SQLException {

        List<CareerType> careerTypes = this.careerTypeDAO.queryForEq("uuid", careerType.getUuid());
        if (careerTypes.isEmpty()) {
            this.careerTypeDAO.createOrUpdate(careerType);
            return careerType;
        }
        return careerTypes.get(0);
    }

    @Override
    public Listble getAllCareerTypes() {
        return null;
    }
}
