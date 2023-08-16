package mz.org.csaude.mentoring.dao.career;

import com.j256.ormlite.dao.Dao;

import mz.org.csaude.mentoring.model.career.CareerType;

import java.sql.SQLException;

public interface CareerTypeDAO extends Dao<CareerType, Integer> {
    public boolean checkCareerTypeExistance(final String code) throws SQLException;

}
