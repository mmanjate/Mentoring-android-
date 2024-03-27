package mz.org.csaude.mentoring.dao.tutored;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.tutored.Tutored;

public interface TutoredDao extends Dao<Tutored, Integer> {

    public boolean checkTutoredExistance(final String uuid) throws SQLException;
}
