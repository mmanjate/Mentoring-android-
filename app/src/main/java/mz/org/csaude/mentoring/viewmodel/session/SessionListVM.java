package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;

public class SessionListVM extends BaseViewModel {

    private Ronda currRonda;

    private Tutored selectedMentee;

    private List<Session> sessionList;

    private List<Tutored> mentees;


    public SessionListVM(@NonNull Application application) {
        super(application);
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
        notifyPropertyChanged(BR.selectedMentee);
    }

    @Override
    public void preInit() {

    }

    public void createSession() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(SessionActivity.class, params);

    }

    public List<Listble> getRondaMentees() {
        try {
            return Utilities.parseList(getApplication().getTutoredService().getAllOfRonda(this.currRonda), Listble.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
