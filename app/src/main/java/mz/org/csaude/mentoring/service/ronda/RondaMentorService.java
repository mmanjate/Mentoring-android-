package mz.org.csaude.mentoring.service.ronda;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;

public interface RondaMentorService extends BaseService<RondaMentor> {
    RondaMentor savedOrUpdateRondaMentor(RondaMentor rondaMentor) throws SQLException;
}
