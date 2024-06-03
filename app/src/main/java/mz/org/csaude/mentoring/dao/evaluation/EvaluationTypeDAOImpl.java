package mz.org.csaude.mentoring.dao.evaluation;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;

public class EvaluationTypeDAOImpl extends MentoringBaseDaoImpl<EvaluationType, Integer> implements EvaluationTypeDAO {


    public EvaluationTypeDAOImpl(Class<EvaluationType> dataClass) throws SQLException {
        super(dataClass);
    }

    public EvaluationTypeDAOImpl(ConnectionSource connectionSource, Class<EvaluationType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public EvaluationTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<EvaluationType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public EvaluationType getByCode(String code) throws SQLException {
        return queryBuilder().where().eq(EvaluationType.COLUMN_CODE, code).queryForFirst();
    }
}
