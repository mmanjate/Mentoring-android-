package mz.org.csaude.mentoring.service.location;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.location.HealthFacilityDAO;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.user.User;

public class HealthFacilityServiceImpl extends BaseServiceImpl<HealthFacility> implements HealthFacilityService {

    HealthFacilityDAO healthFacilityDAO;



    public HealthFacilityServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.healthFacilityDAO = getDataBaseHelper().getHealthFacilityDAO();
    }

    @Override
    public HealthFacility save(HealthFacility record) throws SQLException {
        this.healthFacilityDAO.create(record);
        return record;
    }

    @Override
    public HealthFacility update(HealthFacility record) throws SQLException {
        this.healthFacilityDAO.update(record);
        return record;
    }

    @Override
    public int delete(HealthFacility record) throws SQLException {
        return this.healthFacilityDAO.delete(record);
    }

    @Override
    public List<HealthFacility> getAll() throws SQLException {
        return this.healthFacilityDAO.queryForAll();
    }

    @Override
    public HealthFacility getById(int id) throws SQLException {
        return this.healthFacilityDAO.queryForId(id);
    }
}
