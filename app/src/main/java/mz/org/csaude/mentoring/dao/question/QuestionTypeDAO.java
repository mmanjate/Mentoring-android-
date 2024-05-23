package mz.org.csaude.mentoring.dao.question;

import com.j256.ormlite.dao.Dao;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.dao.mentorship.MentorshipDAO;
import mz.org.csaude.mentoring.model.question.QuestionType;

public interface QuestionTypeDAO extends Dao<QuestionType, Integer> {
}
