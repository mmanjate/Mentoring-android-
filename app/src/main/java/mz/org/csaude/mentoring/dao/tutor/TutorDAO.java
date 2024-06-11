package mz.org.csaude.mentoring.dao.tutor;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface TutorDAO extends MentoringBaseDao<Tutor, Integer> {
   public boolean checkTutorExistance(final String uuid) throws SQLException;

    Tutor getByEmployee(Employee employee) throws SQLException;
}
