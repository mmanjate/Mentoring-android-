package mz.org.csaude.mentoring.dao.rondatype;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

public interface RondaTypeDAO extends MentoringBaseDao<RondaType, Integer> {
    RondaType getRondaTypeByCode(String code) throws SQLException;
    public boolean checkRondaTypeExistance(final String uuid) throws SQLException;
}
