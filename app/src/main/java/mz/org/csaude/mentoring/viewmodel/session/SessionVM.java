package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        this.sessionService = new SessionServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public LocalDateTime getStartDate() {
        return this.session.getStartDate();
    }

    public void setName(LocalDateTime startDate) {
        this.session.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }

    private LocalDateTime endDate;

    @Bindable
    public LocalDateTime getEndDate() {
        return this.session.getEndDate();
    }

    public void setEndDate(LocalDateTime endDate) {
        this.session.setEndDate(endDate);
        notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public LocalDate getPerformedDate() {
        return this.session.getPerformedDate();
    }

    public void setPerformedDate(LocalDate performedDate) {
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
