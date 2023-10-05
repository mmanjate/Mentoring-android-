package mz.org.csaude.mentoring.viewmodel.mentorship;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;

public class ListMentorshipVM extends BaseViewModel {

    private MentorshipService mentorshipService;

    private SessionService sessionService;

    private Session session;

    private List<Mentorship> mentorships;

    public ListMentorshipVM(@NonNull Application application) {
        super(application);

        this.mentorshipService = new MentorshipServiceImpl(application, getCurrentUser());
        this.sessionService = new SessionServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public SessionStatus getSessionStatus() {
        return this.session.getStatus();
    }


    public List<Mentorship> getMentorshipsByTutor(String uuid){
        try {
            return  this.mentorshipService.getMentorshipByTutor(uuid);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
