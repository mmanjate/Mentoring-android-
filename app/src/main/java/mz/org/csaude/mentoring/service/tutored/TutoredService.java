package mz.org.csaude.mentoring.service.tutored;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.tutored.Tutored;

import java.sql.SQLException;
import java.util.List;

public interface TutoredService extends BaseService<Tutored> {

    void savedOrUpdateTutoreds(List<TutoredDTO> tutoredDTOS) throws SQLException;

}
