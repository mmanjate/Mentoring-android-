package mz.org.csaude.mentoring.dao.form;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.form.FormType;

public class FormTypeDAOImpl extends BaseDaoImpl<FormType, Integer> {

    protected FormTypeDAOImpl(Class<FormType> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FormTypeDAOImpl(ConnectionSource connectionSource, Class<FormType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FormTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<FormType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
