package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import mz.org.csaude.mentoring.model.location.Cabinet;

import java.sql.SQLException;

public interface CabinetDAO extends Dao<Cabinet, Integer> {

    public boolean checkCabinetExistance(final String uuid) throws SQLException;
}
