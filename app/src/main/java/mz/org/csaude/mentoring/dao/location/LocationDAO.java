package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.location.Location;

public interface LocationDAO extends Dao<Location, Integer>{

    List<Location> checkLocation(String uuid) throws SQLException;
}
