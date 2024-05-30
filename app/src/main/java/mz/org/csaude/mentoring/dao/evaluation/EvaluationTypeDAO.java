package mz.org.csaude.mentoring.dao.evaluation;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;

public interface EvaluationTypeDAO extends MentoringBaseDao<EvaluationType, Integer> {
    EvaluationType getByCode(String code) throws SQLException;
}
