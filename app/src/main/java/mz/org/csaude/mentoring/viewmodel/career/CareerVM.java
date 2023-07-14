package mz.org.csaude.mentoring.viewmodel.career;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.service.career.CareerServiceImpl;

public class CareerVM extends BaseViewModel {

    private CareerService careerService;
    private Career career;

    public CareerVM(@NonNull Application application) {
        super(application);

        this.careerService = new CareerServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public CareerType getCareerType(){
        return this.career.getCareerType();
    }

    @Bindable
    public String getPosition(){
        return this.career.getPosition();
    }

    public void setCareeType(CareerType careerType){
        this.career.setCareerType(careerType);
        notifyPropertyChanged(BR.careerType);
    }

    public void setPosition(String position){
        this.career.setPosition(position);
        notifyPropertyChanged(BR.position);
    }

    public void save(){
        try {
            this.careerService.save(this.career);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
