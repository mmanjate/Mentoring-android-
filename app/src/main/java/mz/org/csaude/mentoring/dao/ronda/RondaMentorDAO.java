package mz.org.csaude.mentoring.dao.ronda;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;

public interface RondaMentorDAO extends MentoringBaseDao<RondaMentor, Integer> {
    List<RondaMentor> getRondaMentors(Ronda ronda) throws SQLException;

    void deleteByRonda(Ronda ronda) throws SQLException;
}
