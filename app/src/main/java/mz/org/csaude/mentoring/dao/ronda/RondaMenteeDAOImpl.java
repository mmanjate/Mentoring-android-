package mz.org.csaude.mentoring.dao.ronda;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;

public class RondaMenteeDAOImpl extends MentoringBaseDaoImpl<RondaMentee, Integer> implements RondaMenteeDAO {
    public RondaMenteeDAOImpl(Class<RondaMentee> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaMenteeDAOImpl(ConnectionSource connectionSource, Class<RondaMentee> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaMenteeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<RondaMentee> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<RondaMentee> getAllOfRonda(Ronda ronda) throws SQLException {
        return queryBuilder().where().eq(RondaMentee.COLUMN_RONDA, ronda.getId()).query();
    }

    @Override
    public void deleteByRonda(Ronda ronda) throws SQLException {
        // Prepare the delete statement
        DeleteBuilder<RondaMentee, Integer> deleteBuilder = this.deleteBuilder();

        // Add the condition for the deletion
        deleteBuilder.where().eq(RondaMentor.COLUMN_RONDA, ronda.getId());

        // Execute the delete operation
        deleteBuilder.delete();
    }
}
