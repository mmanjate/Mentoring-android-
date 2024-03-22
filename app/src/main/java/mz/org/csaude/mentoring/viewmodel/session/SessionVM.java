package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.Date;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionStatus;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.session.SessionServiceImpl;

public class SessionVM extends BaseViewModel {

    private Session session;

    private SessionService sessionService;

    public SessionVM(@NonNull Application application) {
        super(application);
        this.sessionService = new SessionServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Date getStartDate() {
        return this.session.getStartDate();
    }

    public void setName(Date startDate) {
        this.session.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }

    private Date endDate;

    @Bindable
    public Date getEndDate() {
        return this.session.getEndDate();
    }

    public void setEndDate(Date endDate) {
        this.session.setEndDate(endDate);
        notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public Date getPerformedDate() {
        return this.session.getPerformedDate();
    }

    public void setPerformedDate(Date performedDate) {
        this.session.setPerformedDate(performedDate);
        notifyPropertyChanged(BR.performedDate);
    }

    private SessionStatus status;

    @Bindable
    public SessionStatus getStatus() {
        return this.session.getStatus();
    }

    public void setStatus(SessionStatus status) {
        this.session.setStatus(status);
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getReason() {
        return this.session.getReason();
    }

    public void setReason(String reason) {
        this.session.setReason(reason);
        notifyPropertyChanged(BR.reason);
    }

    public void save() {
        try {
            this.sessionService.save(this.session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
