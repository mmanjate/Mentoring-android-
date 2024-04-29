package mz.org.csaude.mentoring.dao.evaluation;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.dao.question.QuestionDAO;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.question.Question;

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
}
