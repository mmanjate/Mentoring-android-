package mz.org.csaude.mentoring.service.indicator;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.indicator.IndicatorDAO;
import mz.org.csaude.mentoring.model.indicator.Indicator;
import mz.org.csaude.mentoring.model.user.User;

public class IndicatorServiceImpl extends BaseServiceImpl<Indicator> implements IndicatorService {

    IndicatorDAO indicatorDAO;

    public IndicatorServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public IndicatorServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.indicatorDAO = getDataBaseHelper().getIndicatorDAO();
    }

    @Override
    public Indicator save(Indicator record) throws SQLException {
        this.indicatorDAO.create(record);
        return record;
    }

    @Override
    public Indicator update(Indicator record) throws SQLException {
        this.indicatorDAO.update(record);
        return record;
    }

    @Override
    public int delete(Indicator record) throws SQLException {
        return this.indicatorDAO.delete(record);
    }

    @Override
    public List<Indicator> getAll() throws SQLException {
        return this.indicatorDAO.queryForAll();
    }

    @Override
    public Indicator getById(int id) throws SQLException {
        return this.indicatorDAO.queryForId(id);
    }
}
