package mz.org.csaude.mentoring.dao.answer;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public interface AnswerDAO extends Dao<Answer, Integer> {
    List<Answer> queryForMentorship(Mentorship mentorship) throws SQLException;
}
