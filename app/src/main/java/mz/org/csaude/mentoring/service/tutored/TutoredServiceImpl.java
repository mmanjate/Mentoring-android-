package mz.org.csaude.mentoring.service.tutored;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.CareerDAO;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.employee.EmployeeService;
import mz.org.csaude.mentoring.service.employee.EmployeeServiceImpl;

public class TutoredServiceImpl extends BaseServiceImpl<Tutored> implements TutoredService{

    TutoredDao tutoredDao;

    CareerDAO careerDAO;

    EmployeeService employeeService;

    public TutoredServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.tutoredDao = getDataBaseHelper().getTutoredDao();
        this.careerDAO = getDataBaseHelper().getCareerDAO();
        this.employeeService = new EmployeeServiceImpl(application);
    }

    public Tutored save(Tutored tutored) throws SQLException {
        this.tutoredDao.create(tutored);
        return tutored;

    }

    @Override
    public Tutored update(Tutored record) throws SQLException {
        this.tutoredDao.update(record);
        return record;
    }

    @Override
    public int delete(Tutored record) throws SQLException {
        return this.tutoredDao.delete(record);
    }

    @Override
    public List<Tutored> getAll() throws SQLException {
        return this.tutoredDao.queryForAll();
    }

    @Override
    public Tutored getById(int id) throws SQLException {
        return this.tutoredDao.queryForId(id);
    }

    @Override
    public void savedOrUpdateTutoreds(List<Tutored> tutoreds) throws SQLException {
        for (Tutored tutored: tutoreds) {
            savedOrUpdateTutored(tutored);
        }
    }

    @Override
    public Tutored savedOrUpdateTutored(Tutored tutored) throws SQLException {

        Tutored t = this.tutoredDao.getByUuid(tutored.getUuid());
        if (t != null) {
            tutored.setId(t.getId());
        }
        getApplication().getEmployeeService().saveOrUpdateEmployee(tutored.getEmployee());
        this.tutoredDao.createOrUpdate(tutored);
        return tutored;
    }

    @Override
    public List<Tutored> getAllOfRonda(Ronda currRonda) {
        return null;
    }

    @Override
    public List<Tutored> getAllOfHealthFacility(HealthFacility healthFacility) throws SQLException {
        return this.tutoredDao.getAllOfHealthFacility(healthFacility, getApplication());
    }

    @Override
    public List<Tutored> getAllNotSynced() throws SQLException {
        List<Tutored> tutoreds = this.tutoredDao.getAllNotSynced();
        for (Tutored tutored : tutoreds) {
            tutored.getEmployee().setLocations(getApplication().getLocationService().getAllOfEmploee(tutored.getEmployee()));
        }
        return tutoreds;
    }


}
