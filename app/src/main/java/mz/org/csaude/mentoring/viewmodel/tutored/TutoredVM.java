package mz.org.csaude.mentoring.viewmodel.tutored;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;

public class TutoredVM extends BaseViewModel {

    private TutoredService tutoredService;
    private Tutored tutored;

    public TutoredVM(@NonNull Application application) {
        super(application);

        this.tutoredService = new TutoredServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {
    }

    @Bindable
    public String getName() {
        return this.tutored.getName();
    }

    public void setName(String name) {
        this.tutored.setName(name);
        notifyPropertyChanged(BR.name);
    }

    public void save() {
        try {
            this.tutoredService.save(this.tutored);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
