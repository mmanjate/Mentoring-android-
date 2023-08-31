package mz.org.csaude.mentoring.dao.programmaticArea;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface TutorProgrammaticAreaDAO extends Dao<TutorProgrammaticArea, Integer> {
    List<TutorProgrammaticArea> getAllOfTutor(Tutor tutor) throws SQLException;

}
