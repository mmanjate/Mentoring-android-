package mz.org.csaude.mentoring.viewmodel.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.service.location.HealthFacilityService;
import mz.org.csaude.mentoring.service.location.HealthFacilityServiceImpl;

public class HealthFacilityVM extends BaseViewModel {

    private HealthFacility healthFacility;

    private HealthFacilityService healthFacilityService;

    public HealthFacilityVM(@NonNull Application application) {
        super(application);
        this.healthFacilityService = new HealthFacilityServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public District getDistrict() {
        return this.healthFacility.getDistrict();
    }

    public void setDistrict(District district) {
        this.healthFacility.setDistrict(district);
        notifyPropertyChanged(BR.district);
    }

    @Bindable
    public String getHealthFacility() {
        //return this.healthFacility.getHealthFacility();
        return "";
    }

    public void setHealthFacility(String healthFacility) {
       // this.healthFacility.setHealthFacility(healthFacility);
        notifyPropertyChanged(BR.district);
    }

    public void save() {
        try {
            this.healthFacilityService.save(this.healthFacility);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
