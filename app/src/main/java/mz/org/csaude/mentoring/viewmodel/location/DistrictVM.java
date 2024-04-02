package mz.org.csaude.mentoring.viewmodel.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.service.location.DistrictService;
import mz.org.csaude.mentoring.service.location.DistrictServiceImpl;

public class DistrictVM extends BaseViewModel {

    private District district;

    private DistrictService districtService;

    public DistrictVM(@NonNull Application application) {
        super(application);
        this.districtService = new DistrictServiceImpl(application);
    }

    @Override
    public void preInit() {

    }
    @Bindable
    public Province getProvince() {
        return this.district.getProvince();
    }

    public void setProvince(Province province) {
        this.district.setProvince(province);
        notifyPropertyChanged(BR.province);
    }

    @Bindable
    public String getDistrict() {
        return this.district.getDescription();
    }

    public void setProvince(String district) {
        this.district.setDescription(district);
        notifyPropertyChanged(BR.district);
    }


    public void save() {
        try {
            this.districtService.save(this.district);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<District> getAllDistricts(){
        try {
            return this.districtService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
