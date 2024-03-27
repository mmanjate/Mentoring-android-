package mz.org.csaude.mentoring.dao.career;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.career.CareerType;

public interface CareerTypeDAO extends Dao<CareerType, Integer> {
    public boolean checkCareerTypeExistance(final String code) throws SQLException;

}
