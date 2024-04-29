package mz.org.csaude.mentoring.dao.mentorship;

import android.app.Application;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public interface MentorshipDAO extends MentoringBaseDao<Mentorship, Integer> {
     List<Mentorship> getMentorshipByTutor(Application application, String uuidTutor)throws SQLException;

}
