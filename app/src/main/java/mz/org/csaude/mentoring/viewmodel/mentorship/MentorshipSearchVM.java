package mz.org.csaude.mentoring.viewmodel.mentorship;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;
import mz.org.csaude.mentoring.view.mentorship.CreateMentorshipActivity;

public class MentorshipSearchVM extends BaseViewModel {
    private MentorshipService mentorshipService;
    private SessionService sessionService;
    private Mentorship mentorship;
    private Session session;
    private List<Listble> mentorships;
    private List<Listble> sessions;
    public MentorshipSearchVM(@NonNull Application application) {
        super(application);
        this.mentorshipService = new MentorshipServiceImpl(application);
        this.sessionService = new SessionServiceImpl(application);
        this.mentorships = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    public String getMentorshipTitle() {
        return "Sessão 2-Lista de Avaliações Valter Luis";
    }
    @Override
    public void preInit() {
        if (getCurrentStep().isApplicationStepEdit()) {
            this.mentorship = (Mentorship) this.selectedListble;
            this.session = (Session) this.selectedListble;
         } else {
            this.mentorship = new Mentorship();
            this.session = new Session();
        }
        this.mentorships = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    @Bindable
    public SessionStatus getSessionStatus() {
        return this.session.getStatus();
    }

    public void createNewMentorship() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(CreateMentorshipActivity.class, params);
    }


}
