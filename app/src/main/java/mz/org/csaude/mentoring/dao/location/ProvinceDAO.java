package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface ProvinceDAO extends MentoringBaseDao<Province, Integer> {

    public boolean checkProvinceExistance(String uuid) throws SQLException;

    List<Province> getAllOfTutor(Tutor tutor) throws SQLException;
}
