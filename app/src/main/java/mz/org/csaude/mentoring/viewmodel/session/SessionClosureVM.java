package mz.org.csaude.mentoring.viewmodel.session;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.util.Date;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.service.session.SessionClosureService;

public class SessionClosureVM extends BaseViewModel {
    private Session session;

    private Date endDate;
    private SessionClosureService sessionClosureService;

    public SessionClosureVM(@NonNull Application application) {
        super(application);
        this.sessionClosureService = getApplication().getSessionClosureService();
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Date getEndDate() {
        return this.session.getEndDate();
    }

    public void setEndtDate(Date startDate) {
        this.session.setEndDate(startDate);
        notifyPropertyChanged(BR.endDate);
    }


    public void nextStep() {

    }
}
