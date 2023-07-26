package mz.org.csaude.mentoring.dao.mentorship;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;

public class TimeOfDayDAOImpl extends BaseDaoImpl<TimeOfDay, Integer> implements TimeOfDayDAO {

    protected TimeOfDayDAOImpl(Class<TimeOfDay> dataClass) throws SQLException {
        super(dataClass);
    }

    protected TimeOfDayDAOImpl(ConnectionSource connectionSource, Class<TimeOfDay> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected TimeOfDayDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<TimeOfDay> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
