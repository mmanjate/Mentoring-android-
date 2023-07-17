package mz.org.csaude.mentoring.dao.programmaticArea;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;

public class TutorProgrammaticAreaDAOImpl extends BaseDaoImpl<TutorProgrammaticArea, Integer> implements
TutorProgrammaticAreaDAO{

    protected TutorProgrammaticAreaDAOImpl(Class<TutorProgrammaticArea> dataClass) throws SQLException {
        super(dataClass);
    }

    protected TutorProgrammaticAreaDAOImpl(ConnectionSource connectionSource, Class<TutorProgrammaticArea> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected TutorProgrammaticAreaDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<TutorProgrammaticArea> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
