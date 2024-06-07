package mz.org.csaude.mentoring.service.ronda;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;

public interface RondaMenteeService extends BaseService<RondaMentee> {
    RondaMentee savedOrUpdateRondaMentee(RondaMentee rondaMentee) throws SQLException;

    List<RondaMentee> getAllOfRonda(Ronda ronda) throws SQLException;
}
