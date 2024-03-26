package mz.org.csaude.mentoring.service.ronda;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.user.User;

public class RondaServiceImpl extends BaseServiceImpl<Ronda> implements RondaService {

    public RondaServiceImpl(Application application) {
        super(application);
    }

    @Override
    public Ronda save(Ronda record) throws SQLException {
        return null;
    }

    @Override
    public Ronda update(Ronda record) throws SQLException {
        return null;
    }

    @Override
    public int delete(Ronda record) throws SQLException {
        return 0;
    }

    @Override
    public List<Ronda> getAll() throws SQLException {
        return null;
    }

    @Override
    public Ronda getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Ronda> doSearch(long offset, long limit) {
        return null;
    }
}
