package mz.org.csaude.mentoring.dao.programmaticArea;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.dto.programmaticArea.TutorProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface TutorProgrammaticAreaDAO extends MentoringBaseDao<TutorProgrammaticArea, Integer> {
    List<TutorProgrammaticArea> getAllOfTutor(Tutor tutor) throws SQLException;
}
