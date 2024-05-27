package mz.org.csaude.mentoring.dao.location;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.Location;

public interface LocationDAO extends MentoringBaseDao<Location, Integer> {

    List<Location> checkLocation(String uuid) throws SQLException;

    List<Location> getAllOfEmploee(Employee employee) throws SQLException;
}
