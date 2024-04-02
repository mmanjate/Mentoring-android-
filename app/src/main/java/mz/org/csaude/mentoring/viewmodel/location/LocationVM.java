package mz.org.csaude.mentoring.viewmodel.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.service.location.LocationService;

public class LocationVM extends BaseViewModel {

    private Location location;

    private LocationService locationService;

    public LocationVM(@NonNull Application application) {
        super(application);
        this.locationService = getApplication().getLocationService();
    }

    @Override
    public void preInit() {

    }
    @Bindable
    public Listble getProvince(){
       return this.location.getProvince();
    }
    public void setProvince(Listble province){
        this.location.setProvince((Province) province);
    }
    @Bindable
    public Listble getDistrict(){
        return this.location.getDistrict();
    }

    public void setDistrict(Listble district){
        this.location.setDistrict((District) district);
    }
    @Bindable
    public Listble getHealthFacility(){
        return this.location.getHealthFacility();
    }

    public void setHealthFacility(Listble healthFacility){
        this.location.setHealthFacility((HealthFacility) healthFacility);
    }
    @Bindable
    public String getLocationLevel(){
        return this.location.getLocationLevel();
    }

    public void setLocationLevel(String locationLevel){
        this.location.setLocationLevel(locationLevel);
    }
   public List<Location> getAllLocations(){
       try {
           return this.locationService.getAll();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    public Location saveOrUpdate() {
        Location location1 = null;
        try {
            location1 = this.locationService.saveOrUpdate(this.location);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location1;
    }
}
