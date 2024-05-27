package mz.org.csaude.mentoring.dao.employee;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.employee.Employee;

public class EmployeeDAOImpl extends MentoringBaseDaoImpl<Employee, Integer> implements EmployeeDAO {

    public EmployeeDAOImpl(Class<Employee> dataClass) throws SQLException {
        super(dataClass);
    }

    public EmployeeDAOImpl(ConnectionSource connectionSource, Class<Employee> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public EmployeeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Employee> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

}
