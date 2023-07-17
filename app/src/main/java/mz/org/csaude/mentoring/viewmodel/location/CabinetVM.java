package mz.org.csaude.mentoring.viewmodel.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.service.location.CabinetService;
import mz.org.csaude.mentoring.service.location.CabinetServiceImpl;

public class CabinetVM extends BaseViewModel {

    private Cabinet cabinet;

    private CabinetService cabinetService;

    public CabinetVM(@NonNull Application application) {
        super(application);
        this.cabinetService = new CabinetServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getName() {
        return this.cabinet.getName();
    }

    public void setName(String name) {
        this.cabinet.setName(name);
        notifyPropertyChanged(BR.name);
    }


       public void save() {
        try {
            this.cabinetService.save(this.cabinet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
