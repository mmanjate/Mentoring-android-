package mz.org.csaude.mentoring.service.location;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.location.ProvinceDAO;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;

public class ProvinceServiceImpl extends BaseServiceImpl<Province> implements ProvinceService {

    ProvinceDAO provinceDAO;

    public ProvinceServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public ProvinceServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.provinceDAO = getDataBaseHelper().getProvinceDAO();
    }

    @Override
    public Province save(Province record) throws SQLException {
        this.provinceDAO.create(record);
        return record;
    }

    @Override
    public Province update(Province record) throws SQLException {
        this.provinceDAO.update(record);
        return record;
    }

    @Override
    public int delete(Province record) throws SQLException {
        return this.provinceDAO.delete(record);
    }

    @Override
    public List<Province> getAll() throws SQLException {
        return this.provinceDAO.queryForAll();
    }

    @Override
    public Province getById(int id) throws SQLException {
        return this.provinceDAO.queryForId(id);
    }

    @Override
    public Listble getAllOfTutor(Tutor tutor) {
        return null;
    }
}
