package mz.org.csaude.mentoring.dao.mentorship;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.mentorship.IterationType;

public interface IterationTypeDAO extends MentoringBaseDao<IterationType, Integer> {
    IterationType getByCode(String code) throws SQLException;
}
