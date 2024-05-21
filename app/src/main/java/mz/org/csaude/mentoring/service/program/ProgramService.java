package mz.org.csaude.mentoring.service.program;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.program.ProgramDTO;
import mz.org.csaude.mentoring.model.program.Program;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

public interface ProgramService extends BaseService<Program> {
    void saveOrUpdatePrograms(List<ProgramDTO> programDTOS) throws SQLException;
    Program saveOrUpdateProgram(ProgramDTO programDTO) throws SQLException;
}
