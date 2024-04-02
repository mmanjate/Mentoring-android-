package mz.org.csaude.mentoring.viewmodel.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.service.location.ProvinceService;
import mz.org.csaude.mentoring.service.location.ProvinceServiceImpl;

public class ProvinceVM extends BaseViewModel {

    private Province province;
    private ProvinceService provinceService;
    public ProvinceVM(@NonNull Application application) {
        super(application);

        this.provinceService = new ProvinceServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getDescription(){
      return   this.province.getDescription();
    }
    @Bindable
    public void setDescription(String description){
      this.province.setDescription(description);
    }

    @Bindable
    public Listble getProvince() {
        return this.getProvince();
    }

    @Bindable
    public void setProvince(Listble province) {
        this.province =(Province) province;
    }

    @Bindable
    public List<Province> getAllProvince() throws SQLException{
        return this.provinceService.getAll();
    }
}
