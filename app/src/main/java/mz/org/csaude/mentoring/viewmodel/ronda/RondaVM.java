package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.Date;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.ronda.RondaService;

public class RondaVM extends BaseViewModel {

    private RondaService rondaService;
    private Ronda ronda;

    public RondaVM(@NonNull Application application) {
        super(application);

        rondaService = getApplication().getRondaService();
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Date getStartDate() {
        return this.ronda.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.ronda.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public Listble getHealthFacility() {
        return this.ronda.getHealthFacility();
    }

    public void setHealthFacility(Listble healthFacility) {
        this.ronda.setHealthFacility((HealthFacility) healthFacility);
        notifyPropertyChanged(BR.healthFacility);
    }

    public void addMentee(Listble mentee) {
        if (this.ronda.getRondaMentees() == null) this.ronda.setRondaMentees(new ArrayList<>());
        this.ronda.getRondaMentees().add(RondaMentee.fastCreate(this.ronda, (Tutored) mentee));
    }
}
