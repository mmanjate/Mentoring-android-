package mz.org.csaude.mentoring.dao.tutor;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.tutor.TutorLocation;

public class TutorLocationDAOImpl extends BaseDaoImpl<TutorLocation, Integer> implements TutorLocationDAO {


    public TutorLocationDAOImpl(Class<TutorLocation> dataClass) throws SQLException {
        super(dataClass);
    }

    public TutorLocationDAOImpl(ConnectionSource connectionSource, Class<TutorLocation> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public TutorLocationDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<TutorLocation> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
