package mz.org.csaude.mentoring.dao.programmaticArea;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

public class ProgrammaticAreaDAOImpl extends BaseDaoImpl<ProgrammaticArea, Integer> implements ProgrammaticAreaDAO {
    protected ProgrammaticAreaDAOImpl(Class<ProgrammaticArea> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ProgrammaticAreaDAOImpl(ConnectionSource connectionSource, Class<ProgrammaticArea> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ProgrammaticAreaDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<ProgrammaticArea> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
