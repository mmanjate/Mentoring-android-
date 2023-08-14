package mz.org.csaude.mentoring.dao.programmaticArea;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;

public class TutorProgrammaticAreaDAOImpl extends BaseDaoImpl<TutorProgrammaticArea, Integer> implements
TutorProgrammaticAreaDAO{

    public TutorProgrammaticAreaDAOImpl(Class<TutorProgrammaticArea> dataClass) throws SQLException {
        super(dataClass);
    }

    public TutorProgrammaticAreaDAOImpl(ConnectionSource connectionSource, Class<TutorProgrammaticArea> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public TutorProgrammaticAreaDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<TutorProgrammaticArea> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
