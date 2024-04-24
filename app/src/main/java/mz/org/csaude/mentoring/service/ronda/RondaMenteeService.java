package mz.org.csaude.mentoring.service.ronda;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;

public interface RondaMenteeService extends BaseService<RondaMentee> {
    RondaMentee savedOrUpdateRondaMentee(RondaMentee rondaMentee) throws SQLException;
}
