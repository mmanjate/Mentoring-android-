package mz.org.csaude.mentoring.dao.form;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.form.Form;

public class FormDAOImpl extends BaseDaoImpl<Form, Integer> implements FormDAO {

    protected FormDAOImpl(Class<Form> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FormDAOImpl(ConnectionSource connectionSource, Class<Form> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FormDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Form> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
