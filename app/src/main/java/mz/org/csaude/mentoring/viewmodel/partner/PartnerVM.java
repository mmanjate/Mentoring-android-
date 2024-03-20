package mz.org.csaude.mentoring.viewmodel.partner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.service.partner.PartnerService;
import mz.org.csaude.mentoring.service.partner.PartnerServiceImpl;

public class PartnerVM extends BaseViewModel {

    private Partner partner;

    private PartnerService partnerService;

    public PartnerVM(@NonNull Application application) {
        super(application);

        this.partnerService = new PartnerServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getName(){
        return this.partner.getName();
    }

    @Bindable
    public String getDescription(){ return this.partner.getDescription();}

    public void setName(String name){
        this.partner.setName(name);
        notifyPropertyChanged(BR.name);
    }

    public void setDescription(String description){
        this.partner.setDescription(description);
        notifyPropertyChanged(BR.description);
    }

    public void save(){
        try {
         this.partnerService.save(this.partner);
        }catch (SQLException e){
          e.printStackTrace();
        }
    }
}
