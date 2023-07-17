package mz.org.csaude.mentoring.viewmodel.tutor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutor.TutorLocation;
import mz.org.csaude.mentoring.service.tutor.TutorLocationService;

public class TutorLocationVM extends BaseViewModel {

    private TutorLocation tutorLocation;


    private TutorLocationService tutorLocationService;


    public TutorLocationVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Tutor getTutor() {
        return this.tutorLocation.getTutor();
    }

    public void setTutor(Tutor tutor) {
        this.tutorLocation.setTutor(tutor);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public HealthFacility getHealthFacility() {
        return this.tutorLocation.getHealthFacility();
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.tutorLocation.setHealthFacility(healthFacility);
        notifyPropertyChanged(BR.healthFacility);
    }


    public void save() {
        try {
            this.tutorLocationService.save(this.tutorLocation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
