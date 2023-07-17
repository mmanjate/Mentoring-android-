package mz.org.csaude.mentoring.dao.form;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.form.FormTarget;

public class FormTargetDAOImpl extends BaseDaoImpl<FormTarget, Integer> implements FormTargetDAO {


    protected FormTargetDAOImpl(Class<FormTarget> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FormTargetDAOImpl(ConnectionSource connectionSource, Class<FormTarget> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FormTargetDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<FormTarget> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
