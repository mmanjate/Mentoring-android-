package mz.org.csaude.mentoring.service.ProgrammaticArea;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.programmaticArea.ProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

public interface ProgrammaticAreaService extends BaseService<ProgrammaticArea> {
    void saveOrUpdateProgrammaticAreas(List<ProgrammaticAreaDTO> programmaticAreaDTOS) throws SQLException;
    ProgrammaticArea saveOrUpdateProgrammaticArea(ProgrammaticAreaDTO programmaticAreaDTO) throws SQLException;

}
