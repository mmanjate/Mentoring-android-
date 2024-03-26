package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.location.Cabinet;

public interface CabinetDAO extends Dao<Cabinet, Integer> {

    public boolean checkCabinetExistance(final String uuid) throws SQLException;
}
