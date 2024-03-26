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

    private Date endDate;

    private SessionStatus status;

    public SessionVM(@NonNull Application application) {
        super(application);
        this.sessionService = getApplication().getSessionService();
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Date getStartDate() {
        return this.session.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.session.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }




    public void save() {
        try {
            this.sessionService.save(this.session);
        } catch (SQLException e) {
            e.printStackTrace();
            //Mostrar mensagem de erro
        }
    }
}
