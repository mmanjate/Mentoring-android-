package mz.org.csaude.mentoring.service.mentorship;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.mentorship.TimeOfDayDTO;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;

public interface TimeOfDayService extends BaseService<TimeOfDay> {
    void saveOrUpdateTimesOfDay(List<TimeOfDayDTO> timeOfDayDTOS) throws SQLException;
    TimeOfDay saveOrUpdateTimeOfDay(TimeOfDayDTO timeOfDayDTO) throws SQLException;
}
