package mz.org.csaude.mentoring.dao.program;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.program.Program;
public class ProgramDAOImpl extends MentoringBaseDaoImpl<Program, Integer> implements ProgramDAO {
    public ProgramDAOImpl(Class<Program> dataClass) throws SQLException {
        super(dataClass);
    }

    public ProgramDAOImpl(ConnectionSource connectionSource, Class<Program> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ProgramDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Program> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
