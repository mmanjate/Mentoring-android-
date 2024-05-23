package mz.org.csaude.mentoring.dao.location;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface DistrictDAO extends MentoringBaseDao<District, Integer> {

    public boolean checkDistrictxistance(final String uuid) throws SQLException;
    List<District> getByProvince(Province province) throws SQLException;

    List<District> getByProvinceAndMentor(Province province, Tutor mentor) throws SQLException;
}
