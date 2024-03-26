package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;

public class RondaVM extends BaseViewModel {

    private RondaService rondaService;
    private Ronda ronda;

    private Province selectedProvince;

    private District selectedDistrict;

    private List<Province> provinces;
    private List<District> districts;

    private List<HealthFacility> healthFacilities;

    private Tutored selectedMentee;

    public RondaVM(@NonNull Application application) {
        super(application);

        rondaService = getApplication().getRondaService();
    }

    @Override
    public RondaActivity getRelatedActivity() {
        return (RondaActivity) super.getRelatedActivity();
    }

    @Override
    public void preInit() {
        try {
            this.provinces = getApplication().getProvinceService().getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Bindable
    public Listble getSelectedProvince() {
        return selectedProvince;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setSelectedProvince(Listble selectedProvince) {
        this.selectedProvince = (Province) selectedProvince;
        try {
            districts.clear();
            districts.add(new District());
            districts.addAll(getApplication().getDistrictService().getByProvince(this.selectedProvince));
            getRelatedActivity().reloadDistrcitAdapter();
            notifyPropertyChanged(BR.selectedProvince);
            this.setSelectedDistrict(null);
            this.setHealthFacility(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bindable
    public Listble getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(Listble selectedDistrict) {
        this.selectedDistrict = (District) selectedDistrict;
        notifyPropertyChanged(BR.selectedDistrict);
    }

    @Bindable
    public Tutored getSelectedMentee() {
        return selectedMentee;
    }

    public void setSelectedMentee(Tutored selectedMentee) {
        this.selectedMentee = selectedMentee;
        notifyPropertyChanged(BR.selectedMentee);
    }

    public void addSelectedMentee() {

    }
}
