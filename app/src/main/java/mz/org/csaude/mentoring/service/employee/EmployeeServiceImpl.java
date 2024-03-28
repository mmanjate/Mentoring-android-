package mz.org.csaude.mentoring.service.employee;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.employee.EmployeeDAO;
import mz.org.csaude.mentoring.dao.location.LocationDAO;
import mz.org.csaude.mentoring.dao.partner.PartnerDao;
import mz.org.csaude.mentoring.dao.professionalCategoryDAO.ProfessionalCategoryDAO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.Location;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

    private EmployeeDAO employeeDAO;

    private ProfessionalCategoryDAO professionalCategoryDAO;
    private LocationDAO locationDAO;

    private PartnerDao partnerDao;

    public EmployeeServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application ) throws SQLException {
        super.init(application );
        this.employeeDAO = getDataBaseHelper().getEmployeeDAO();
        professionalCategoryDAO = dataBaseHelper.getProfessionalCategoryDAO();
        locationDAO = dataBaseHelper.getLocationDAO();
        partnerDao = dataBaseHelper.getPartnerDao();
    }

    @Override
    public Employee save(Employee record) throws SQLException {
        this.employeeDAO.create(record);
        return record;
    }

    @Override
    public Employee update(Employee record) throws SQLException {
        this.employeeDAO.update(record);
        return record;
    }

    @Override
    public int delete(Employee record) throws SQLException {
        return this.employeeDAO.delete(record);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return this.employeeDAO.queryForAll();
    }

    @Override
    public Employee getById(int id) throws SQLException {
        return this.employeeDAO.queryForId(id);
    }

    @Override
    public void saveOrUpdateEmployees(List<EmployeeDTO> employeeDTOS) throws SQLException {

        for (EmployeeDTO employeeDTO : employeeDTOS){

            this.saveOrUpdateEmployee(new Employee(employeeDTO));
        }
    }

    @Override
    public Employee saveOrUpdateEmployee(Employee e) throws SQLException {

        Employee employee = this.employeeDAO.getByUuid(e.getUuid());
        if(employee == null){
            e.setProfessionalCategory(professionalCategoryDAO.getByUuid(e.getProfessionalCategory().getUuid()));
            e.setPartner(partnerDao.getByUuid(e.getPartner().getUuid()));
            this.employeeDAO.createOrUpdate(e);
            saveLocationFromEmplyee(e.getLocations());
            return e;
        } else {
            employee.setProfessionalCategory(professionalCategoryDAO.getByUuid(e.getProfessionalCategory().getUuid()));
            employee.setPartner(partnerDao.getByUuid(e.getPartner().getUuid()));
            this.employeeDAO.createOrUpdate(employee);
            saveLocationFromEmplyee(employee.getLocations());
            return e;
        }
    }

    private void saveLocationFromEmplyee(Set<Location> locations) throws SQLException {

        for (Location location : locations){
            if (location.getProvince() != null) location.setProvince(getDataBaseHelper().getProvinceDAO().getByUuid(location.getProvince().getUuid()));
            if (location.getDistrict() != null) location.setDistrict(getDataBaseHelper().getDistrictDAO().getByUuid(location.getDistrict().getUuid()));
            if (getDataBaseHelper().getHealthFacilityDAO().getByUuid(location.getHealthFacility().getUuid()) == null) {
                getDataBaseHelper().getHealthFacilityDAO().create(location.getHealthFacility());
            }
            //if (location.getHealthFacility() != null) location.setHealthFacility(getDataBaseHelper().getHealthFacilityDAO().getByUuid(location.getHealthFacility().getUuid()));

            getApplication().getLocationService().saveOrUpdate(location);
        }
    }
}
