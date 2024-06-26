package mz.org.csaude.mentoring.dao.resource;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.resourceea.Resource;

public class ResourceDaoImpl extends MentoringBaseDaoImpl<Resource, Integer> implements ResourceDAO {

    public ResourceDaoImpl(Class dataClass) throws SQLException {
        super(dataClass);
    }

    public ResourceDaoImpl(ConnectionSource connectionSource, Class dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ResourceDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkResourceExistance(String uuid) throws SQLException {

        List<Resource> resources = this.queryForEq("uuid", uuid);
        return !resources.isEmpty();
    }


}
