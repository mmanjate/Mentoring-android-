package mz.org.csaude.mentoring.service.partner;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.partner.PartnerDao;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.user.User;

public class PartnerServiceImpl extends BaseServiceImpl<Partner> implements PartnerService {

    PartnerDao partnerDao;

    public PartnerServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public PartnerServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException{
        super.init(application, currentUser);
        this.partnerDao = getDataBaseHelper().getPartnerDao();
    }


    @Override
    public Partner save(Partner record) throws SQLException {
        this.partnerDao.create(record);
        return record;
    }

    @Override
    public Partner update(Partner record) throws SQLException {
        this.partnerDao.update(record);
        return record;
    }

    @Override
    public int delete(Partner record) throws SQLException {
        return this.partnerDao.delete(record);
    }

    @Override
    public List<Partner> getAll() throws SQLException {
        return this.partnerDao.queryForAll();
    }

    @Override
    public Partner getById(int id) throws SQLException {
        return this.getById(id);
    }
}
