package mz.org.csaude.mentoring.service.ronda;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.ronda.RondaDAO;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class RondaServiceImpl extends BaseServiceImpl<Ronda> implements RondaService {
    RondaDAO rondaDAO;

    public RondaServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.rondaDAO = getDataBaseHelper().getRondaDAO();
    }

    @Override
    public Ronda savedOrUpdateRonda(Ronda ronda) throws SQLException {
        Ronda r = this.rondaDAO.getByUuid(ronda.getUuid());
        if(r!=null) {
            ronda.setId(r.getId());
        }
        this.rondaDAO.createOrUpdate(ronda);
        return ronda;
    }

    @Override
    public List<Ronda> getAllByHealthFacilityAndMentor(HealthFacility healthFacility, Tutor tutor, MentoringApplication mentoringApplication) throws SQLException {
        return this.rondaDAO.getAllByHealthFacilityAndMentor(healthFacility, tutor, mentoringApplication);
    }

    @Override
    public List<Ronda> getAllNotSynced() throws SQLException {
        return this.rondaDAO.getAllNotSynced();
    }

    @Override
    public List<Ronda> doSearch(long offset, long limit) {
        return null;
    }

    @Override
    public int countRondas() throws SQLException {
        return this.rondaDAO.queryForAll().size();
    }

    @Override
    public Ronda save(Ronda record) throws SQLException {
        this.rondaDAO.create(record);
        return record;
    }

    @Override
    public Ronda update(Ronda record) throws SQLException {
        this.rondaDAO.update(record);
        return record;
    }

    @Override
    public int delete(Ronda record) throws SQLException {
        return this.rondaDAO.delete(record);
    }

    @Override
    public List<Ronda> getAll() throws SQLException {
        return this.rondaDAO.queryForAll();
    }

    @Override
    public Ronda getById(int id) throws SQLException {
        return this.rondaDAO.queryForId(id);
    }
}
