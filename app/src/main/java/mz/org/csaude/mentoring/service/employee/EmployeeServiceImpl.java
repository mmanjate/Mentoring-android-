package mz.org.csaude.mentoring.service.employee;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.employee.EmployeeDAO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.dto.professionalcategory.ProfessionalCategoryDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.location.LocationService;
import mz.org.csaude.mentoring.service.location.LocationServiceImpls;
import mz.org.csaude.mentoring.service.partner.PartnerService;
import mz.org.csaude.mentoring.service.partner.PartnerServiceImpl;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryService;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryServiceImpl;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

    private EmployeeDAO employeeDAO;

    private ProfessionalCategoryService professionalCategoryService;

    private PartnerService partnerService;

    private LocationService locationService;

    public EmployeeServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application ) throws SQLException {
        super.init(application );
        this.employeeDAO = getDataBaseHelper().getEmployeeDAO();
        this.professionalCategoryService = new ProfessionalCategoryServiceImpl(application );
        this.partnerService = new PartnerServiceImpl(application );
        this.locationService = new LocationServiceImpls(application );
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
    public Employee saveOrUpdateEmployee(Employee employee) throws SQLException {

        List<Employee> employees = this.employeeDAO.queryForEq("uuid", employee.getUuid());
        if(employees.isEmpty()){
            ProfessionalCategory professionalCategory = this.professionalCategoryService.saveOrUpdateProfessionalCategory(new ProfessionalCategoryDTO(employee.getProfessionalCategory()));
          if(employee.getPartner() != null ) {
              Partner partner = this.partnerService.savedOrUpdatePartner((Partner) employee.getPartner());
              employee.setPartner(partner);
          }
            Set<Location> locations = employee.getLocations();
           // this.saveLocationFromEmplyee(locations);
            employee.setProfessionalCategory(professionalCategory);

            this.employeeDAO.createOrUpdate(employee);
            return employee;
        }
        return employees.get(0);
    }

    private void saveLocationFromEmplyee(Set<Location> locations) throws SQLException {

        for (Location location : locations){
            this.locationService.saveOrUpdate(location);
        }
    }
}
