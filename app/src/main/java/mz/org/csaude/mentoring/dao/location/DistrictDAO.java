package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;

public interface DistrictDAO extends Dao<District, Integer> {
    List<District> getByProvince(Province province) throws SQLException;
}
