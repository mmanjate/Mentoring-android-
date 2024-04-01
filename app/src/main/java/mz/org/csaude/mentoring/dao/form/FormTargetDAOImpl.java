package mz.org.csaude.mentoring.dao.form;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.form.FormTarget;

public class FormTargetDAOImpl extends MentoringBaseDaoImpl<FormTarget, Integer> implements FormTargetDAO {


    public FormTargetDAOImpl(Class<FormTarget> dataClass) throws SQLException {
        super(dataClass);
    }

    public FormTargetDAOImpl(ConnectionSource connectionSource, Class<FormTarget> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public FormTargetDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<FormTarget> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
