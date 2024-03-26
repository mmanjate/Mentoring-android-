package mz.org.csaude.mentoring.service.tutored;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.CareerDAO;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
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
    public void savedOrUpdateTutoreds(List<TutoredDTO> tutoredDTOS) throws SQLException {
        for (TutoredDTO tutoredDTO: tutoredDTOS) {
            boolean doesTutoredExist = this.tutoredDao.checkTutoredExistance(tutoredDTO.getUuid());
            if(!doesTutoredExist){
                Employee employee = this.employeeService.saveOrUpdateEmployee(new Employee(tutoredDTO.getEmployeeDTO()));

                Tutored tutored = new Tutored(tutoredDTO);
                tutored.setEmployee(employee);
                this.tutoredDao.createOrUpdate(tutored);
            }
        }

    }

    @Override
    public List<Tutored> getAllOfRonda(Ronda currRonda) {
        return null;
    }
}
