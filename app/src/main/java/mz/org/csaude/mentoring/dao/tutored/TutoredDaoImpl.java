package mz.org.csaude.mentoring.dao.tutored;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.tutored.Tutored;

public class TutoredDaoImpl extends BaseDaoImpl<Tutored, Integer> implements TutoredDao {

    public TutoredDaoImpl(Class<Tutored> dataClass) throws SQLException {
        super(dataClass);
    }

    public TutoredDaoImpl(ConnectionSource connectionSource, Class<Tutored> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public TutoredDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Tutored> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkTutoredExistance(String uuid) throws SQLException {
        List<Tutored> tutoreds = this.queryForEq("uuid", uuid);
        return !tutoreds.isEmpty();
    }
}
