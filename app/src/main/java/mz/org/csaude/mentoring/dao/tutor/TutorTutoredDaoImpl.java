package mz.org.csaude.mentoring.dao.tutor;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.tutor.TutorTutored;

public class TutorTutoredDaoImpl extends BaseDaoImpl<TutorTutored, Integer> implements TutorTutoredDao {
    public TutorTutoredDaoImpl(Class<TutorTutored> dataClass) throws SQLException {
        super(dataClass);
    }

    public TutorTutoredDaoImpl(ConnectionSource connectionSource, Class<TutorTutored> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public TutorTutoredDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<TutorTutored> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
