package mz.org.csaude.mentoring.dao.responseType;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.responseType.ResponseType;

public class ResponseTypeDAOImpl extends MentoringBaseDaoImpl<ResponseType, Integer> implements ResponseTypeDAO {


    public ResponseTypeDAOImpl(Class<ResponseType> dataClass) throws SQLException {
        super(dataClass);
    }

    public ResponseTypeDAOImpl(ConnectionSource connectionSource, Class<ResponseType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ResponseTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<ResponseType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
