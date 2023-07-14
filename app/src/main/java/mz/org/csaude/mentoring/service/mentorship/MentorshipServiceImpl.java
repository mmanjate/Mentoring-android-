package mz.org.csaude.mentoring.service.mentorship;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.mentorship.MentorshipDAO;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.user.User;

public class MentorshipServiceImpl extends BaseServiceImpl<Mentorship> implements MentorshipService {


    MentorshipDAO mentorshipDAO;

    public MentorshipServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public MentorshipServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.mentorshipDAO = getDataBaseHelper().getMentorshipDAO();
    }

    @Override
    public Mentorship save(Mentorship record) throws SQLException {
        mentorshipDAO.create(record);
        return record;
    }

    @Override
    public Mentorship update(Mentorship record) throws SQLException {
        mentorshipDAO.update(record);
        return record;
    }

    @Override
    public int delete(Mentorship record) throws SQLException {
        return this.mentorshipDAO.delete(record);
    }

    @Override
    public List<Mentorship> getAll() throws SQLException {
        return this.mentorshipDAO.queryForAll();
    }

    @Override
    public Mentorship getById(int id) throws SQLException {
        return this.mentorshipDAO.queryForId(id);
    }
}
