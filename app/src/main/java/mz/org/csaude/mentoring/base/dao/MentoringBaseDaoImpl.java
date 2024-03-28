package mz.org.csaude.mentoring.base.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.model.BaseModel;

public abstract class MentoringBaseDaoImpl<T, ID> extends BaseDaoImpl<T, ID> implements MentoringBaseDao<T, ID>{
    public MentoringBaseDaoImpl(Class dataClass) throws SQLException {
        super(dataClass);
    }

    public MentoringBaseDaoImpl(ConnectionSource connectionSource, Class dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public MentoringBaseDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public T getByUuid(String uuid) throws SQLException {
        return queryBuilder().where().eq(BaseModel.COLUMN_UUID, uuid).queryForFirst();
    }
}
