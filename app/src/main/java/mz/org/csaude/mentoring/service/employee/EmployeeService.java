package mz.org.csaude.mentoring.service.employee;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.employee.Employee;

public interface EmployeeService extends BaseService<Employee> {
    void saveOrUpdateEmployees(List<EmployeeDTO> employeeDTOS) throws SQLException;
    Employee saveOrUpdateEmployee(Employee employee) throws SQLException;

}
