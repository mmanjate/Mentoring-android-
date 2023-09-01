package mz.org.csaude.mentoring.dao.tutored;

import com.j256.ormlite.dao.Dao;

import mz.org.csaude.mentoring.model.tutored.Tutored;

import java.sql.SQLException;

public interface TutoredDao extends Dao<Tutored, Integer> {

    public boolean checkTutoredExistance(final String uuid) throws SQLException;
}
