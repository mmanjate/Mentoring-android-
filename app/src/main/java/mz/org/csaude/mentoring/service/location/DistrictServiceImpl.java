package mz.org.csaude.mentoring.service.location;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.location.DistrictDAO;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.user.User;

public class DistrictServiceImpl extends BaseServiceImpl<District> implements DistrictService {

     DistrictDAO districtDAO;


    public DistrictServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.districtDAO = getDataBaseHelper().getDistrictDAO();
    }

    @Override
    public District save(District record) throws SQLException {
        this.districtDAO.create(record);
        return record;
    }

    @Override
    public District update(District record) throws SQLException {
        this.districtDAO.update(record);
        return record;
    }

    @Override
    public int delete(District record) throws SQLException {
        return this.districtDAO.delete(record);
    }

    @Override
    public List<District> getAll() throws SQLException {
        return this.districtDAO.queryForAll();
    }

    @Override
    public District getById(int id) throws SQLException {
        return this.districtDAO.queryForId(id);
    }

    @Override
    public List<District> getByProvince(Province selectedProvince) throws SQLException{
        return this.districtDAO.getByProvince(selectedProvince);
    }
}
