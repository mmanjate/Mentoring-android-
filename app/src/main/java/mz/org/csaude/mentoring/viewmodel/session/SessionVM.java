package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;

public class SessionVM extends BaseViewModel {

    private Session session;

    private List<Form> forms;
    public SessionVM(@NonNull Application application) {
        super(application);
        init();
    }

    public void init() {
        try {
            this.session = new Session();
            this.session.setStatus(getApplication().getSessionStatusService().getByCode(SessionStatus.INCOMPLETE));
            this.session.setStartDate(DateUtilities.getCurrentDate());
            this.session.setSyncStatus(SyncSatus.PENDING);
            this.session.setUuid(Utilities.getNewUUID().toString());
            this.session.setCreatedAt(DateUtilities.getCurrentDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void preInit() {

    }

    @Bindable
    public Date getStartDate() {
        return this.session.getStartDate();
    }

    public List<Form> getTutorForms() {
        try {
            this.forms = getApplication().getFormService().getAllOfTutor(getCurrentTutor());
            return forms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectForm(int position) {
        for (Form form : this.forms) {
            if (form.isSelected()) form.setItemSelected(false);
        }
        Form form = this.forms.get(position);
        form.setItemSelected(true);
        session.setForm(form);
    }

    public Session getSession() {
        return session;
    }

    public void setStartDate(Date startDate) {
        this.session.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }
    public void save() {
        try {
            if (this.session.getStartDate().before(this.session.getRonda().getStartDate())) {
                Utilities.displayAlertDialog(getRelatedActivity(), "A data de início da sessão não pode ser anterior a data de início da ronda").show();
                return;
            }
            getApplication().getSessionService().save(this.session);
            getRelatedActivity().finish();
        } catch (SQLException e) {
            Log.e("SessionVM", "save: " + e.getMessage());
        }
    }

    public void setCurrRonda(Ronda ronda) {
        this.session.setRonda(ronda);
    }

    public void setMentee(Tutored mentee) {
        this.session.setTutored(mentee);
    }
}
