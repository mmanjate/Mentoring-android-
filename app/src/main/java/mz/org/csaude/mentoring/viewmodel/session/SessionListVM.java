package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.adapter.recyclerview.session.SessionAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.searchparams.AbstractSearchParams;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.base.viewModel.SearchVM;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.mentorship.MentorshipActivity;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;
import mz.org.csaude.mentoring.view.session.SessionClosureActivity;
import mz.org.csaude.mentoring.view.session.SessionListActivity;
import mz.org.csaude.mentoring.view.session.SessionSummaryActivity;

public class SessionListVM extends SearchVM<Session>  implements IDialogListener {

    private Ronda currRonda;

    private Tutored selectedMentee;

    private Session selectedSession;



    public SessionListVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void doOnNoRecordFound() {
        getRelatedActivity().populateSessions();
        //Utilities.displayAlertDialog(getRelatedActivity(), "Não foram encontradas sessões para o mentorando(a) "+this.selectedMentee.getEmployee().getFullName()).show();
    }

    public Ronda getCurrRonda() {
        return currRonda;
    }

    public void setCurrRonda(Ronda currRonda) {
        this.currRonda = currRonda;
    }

    @Bindable
    public Listble getSelectedMentee() {
        return selectedMentee;
    }


    public void setSelectedMentee(Listble selectedMentee) {
        this.selectedMentee = (Tutored) selectedMentee;
        initSearch();
        notifyPropertyChanged(BR.selectedMentee);
    }

    @Override
    public void preInit() {

    }

    public void createSession() {
        if (this.searchResults.size() < 4) {
            Map<String, Object> params = new HashMap<>();
            params.put("ronda", this.currRonda);
            params.put("mentee", this.selectedMentee);
            getRelatedActivity().nextActivity(SessionActivity.class, params);
        } else {
            Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível criar mais de 4 sessões para o(a) mentorando(a) "+this.selectedMentee.getEmployee().getFullName()).show();
        }
    }

    public List<Listble> getRondaMentees() {
        try {
            return Utilities.parseList(getApplication().getTutoredService().getAllOfRonda(this.currRonda), Listble.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SessionListActivity getRelatedActivity() {
        return (SessionListActivity) super.getRelatedActivity();
    }

    @Override
    public List<Session> doSearch(long offset, long limit) throws SQLException {
        return getApplication().getSessionService().getAllOfRondaAndMentee(this.currRonda, this.selectedMentee, offset, limit);
    }

    @Override
    public void displaySearchResults() {
        getRelatedActivity().populateSessions();
    }

    @Override
    public AbstractSearchParams<Session> initSearchParams() {
        return null;
    }

    public void openSession(Session session) {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        getRelatedActivity().nextActivity(MentorshipActivity.class, params);
    }

    public void deleteSession(Session session) {
        this.selectedSession = session;
        try {
            session.setMentorships(getApplication().getMentorshipService().getAllOfSession(session));
            if (session.isCompleted()) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma sessão finalizada").show();
                return;
            } else if (Utilities.listHasElements(session.getMentorships())) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma sessão com avaliações criadas.").show();
                return;
            }

            Utilities.displayConfirmationDialog(getRelatedActivity(), "Deseja realmente excluir a sessão?", "Sim", "Não", this).show();
        } catch (SQLException e) {
            Log.e("SessionListVM", "editSession: ", e);
        }
    }

    public void editSession(Session session) {
        try {
            session.setMentorships(getApplication().getMentorshipService().getAllOfSession(session));
            if (session.isCompleted()) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma sessão finalizada").show();
                return;
            } else if (Utilities.listHasElements(session.getMentorships())) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma sessão com avaliações criadas.").show();
                return;
            }
        } catch (SQLException e) {
            Log.e("SessionListVM", "editSession: ", e);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        getApplication().getApplicationStep().changeToEdit();
        getRelatedActivity().nextActivity(SessionActivity.class, params);
    }


    public void printSummary(Session session) {
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        if (!session.isCompleted()) {
            getRelatedActivity().nextActivity(SessionClosureActivity.class, params);
        } else {
            getRelatedActivity().nextActivity(SessionSummaryActivity.class, params);
        }
    }

    @Override
    public void doOnConfirmed() {
        try {
            getApplication().getSessionService().delete(this.selectedSession);
            initSearch();
        } catch (SQLException e) {
            Log.e("SessionListVM", "doOnConfirmed: ", e);
        }
    }

    @Override
    public void doOnDeny() {

    }
}
