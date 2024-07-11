package mz.org.csaude.mentoring.service.answer;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public interface AnswerService extends BaseService<Answer> {
    List<Answer> getAllOfMentorship(Mentorship mentorship) throws SQLException;

    void saveOrUpdate(Answer answer) throws SQLException;
}
