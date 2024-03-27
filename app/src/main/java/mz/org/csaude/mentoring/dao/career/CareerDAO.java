package mz.org.csaude.mentoring.dao.career;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;

public interface CareerDAO extends Dao<Career, Integer> {

    public boolean checkCareerExistance(final String uuid) throws SQLException;

    List<Career> findByCareerType(CareerType careerType) throws SQLException;

    public Career findByUuid(String uuid) throws  SQLException;
}
