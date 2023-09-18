package mz.org.csaude.mentoring.service.mentorship;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public interface MentorshipService extends BaseService<Mentorship> {
    public List<Mentorship> getMentorshipByTutor(String uuidTutor) throws SQLException;

}
