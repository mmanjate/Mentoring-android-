package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.Location;

public class LocationDAOImpl extends MentoringBaseDaoImpl<Location, Integer> implements LocationDAO{


    public LocationDAOImpl(Class<Location> dataClass) throws SQLException {
        super(dataClass);
    }

    public LocationDAOImpl(ConnectionSource connectionSource, Class<Location> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public LocationDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Location> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public  List<Location> checkLocation(String uuid) throws SQLException {

        return this.queryForEq("uuid", uuid);
    }

    @Override
    public List<Location> getAllOfEmploee(Employee employee) throws SQLException {
        return queryBuilder().where().eq(Location.COLUMN_EMPLOYEE, employee.getId()).query();
    }
}
