package mz.org.csaude.mentoring.service.ProgrammaticArea;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.programmaticArea.TutorProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;

public interface TutorProgrammaticAreaService extends BaseService<TutorProgrammaticArea> {
    void saveOrUpdateTutorProgrammaticAreas(List<TutorProgrammaticAreaDTO> tutorProgrammaticAreaDTOS) throws SQLException;
    TutorProgrammaticArea saveOrUpdateTutorProgrammaticArea(TutorProgrammaticAreaDTO tutorProgrammaticAreaDTO) throws SQLException;
}
