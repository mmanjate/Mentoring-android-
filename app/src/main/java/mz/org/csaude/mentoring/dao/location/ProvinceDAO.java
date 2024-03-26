package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.location.Province;

public interface ProvinceDAO extends Dao<Province, Integer> {

    public boolean checkProvinceExistance(String uuid) throws SQLException;
}
