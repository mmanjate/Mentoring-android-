package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;

public interface DistrictDAO extends MentoringBaseDao<District, Integer> {

    public boolean checkDistrictxistance(final String uuid) throws SQLException;
    List<District> getByProvince(Province province) throws SQLException;

}
