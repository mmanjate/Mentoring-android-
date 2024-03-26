package mz.org.csaude.mentoring.viewmodel.mentorship;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;
import mz.org.csaude.mentoring.view.mentorship.MentorshipActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;

public class MentorShipListVM extends BaseViewModel {

    private MentorshipService mentorshipService;

    private SessionService sessionService;

    private Session session;

    private List<Mentorship> mentorships;

    public MentorShipListVM(@NonNull Application application) {
        super(application);

        this.mentorshipService = new MentorshipServiceImpl(application);
        this.sessionService = new SessionServiceImpl(application);
    }

    public String getMentorshipTitle() {
        return "Sessão 2-Lista de Avaliações Valter Luis";
    }
    @Override
    public void preInit() {

    }

    @Bindable
    public SessionStatus getSessionStatus() {
        return this.session.getStatus();
    }


    public void createNewMentorship() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(MentorshipActivity.class, params);
    }


}
