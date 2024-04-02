package mz.org.csaude.mentoring.viewmodel.tutored;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.service.career.CareerServiceImpl;
import mz.org.csaude.mentoring.service.employee.EmployeeService;
import mz.org.csaude.mentoring.service.employee.EmployeeServiceImpl;
import mz.org.csaude.mentoring.service.location.DistrictService;
import mz.org.csaude.mentoring.service.location.DistrictServiceImpl;
import mz.org.csaude.mentoring.service.location.HealthFacilityService;
import mz.org.csaude.mentoring.service.location.HealthFacilityServiceImpl;
import mz.org.csaude.mentoring.service.location.LocationService;
import mz.org.csaude.mentoring.service.location.ProvinceService;
import mz.org.csaude.mentoring.service.location.ProvinceServiceImpl;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryService;
import mz.org.csaude.mentoring.service.professionalCategory.ProfessionalCategoryServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import mz.org.csaude.mentoring.util.SimpleValue;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.tutored.CreateTutoredActivity;
import mz.org.csaude.mentoring.view.tutored.TutoredActivity;

public class TutoredVM extends BaseViewModel {
    private TutoredService tutoredService;
    private ProfessionalCategoryService professionalCategoryService;
    private CareerService careerService;
    private SessionService sessionService;
    private Tutored tutored;

    private Location location;

    private Employee employee;

    private Province province;

    private ProfessionalCategory professionalCategory;

    private District district;

    private HealthFacility healthFacility;

    private EmployeeService employeeService;

    private ProvinceService provinceService;

    private DistrictService districtService;

    private HealthFacilityService healthFacilityService;

    private LocationService locationService;

    private boolean initialDataVisible;

    private List<District> districts;

    private List<HealthFacility> healthFacilities;

    private List<SimpleValue> menteeLabors;

    private Partner selectedNgo;

    public TutoredVM(@NonNull Application application) {
        super(application);

        this.initialDataVisible = true;
        this.tutoredService = getApplication().getTutoredService();
        this.professionalCategoryService = getApplication().getProfessionalCategoryService();
        this.provinceService = getApplication().getProvinceService();
        this.districtService = getApplication().getDistrictService();
        this.healthFacilityService = getApplication().getHealthFacilityService();
        this.employeeService = getApplication().getEmployeeService();
        this.locationService = getApplication().getLocationService();
        districts = new ArrayList<>();
        healthFacilities = new ArrayList<>();
        menteeLabors = new ArrayList<>();
        tutored = new Tutored();
        employee = new Employee();
        location = new Location();
        loadMeteeLabors();
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getName() {
        return this.employee.getName();
    }

    public void setName(String name) {
        this.employee.setName(name);
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getSurname(){
        return this.employee.getSurname();
    }

    public void setSurname(String surname){
       this.employee.setSurname(surname);
    }
    @Bindable
    public int getNuit() {
        return this.employee.getNuit();
    }
    public void setNuit(int nuit) {
        this.employee.setNuit(nuit);
    }
    public List<ProfessionalCategory> getAllProfessionalCategys() throws SQLException{
        return this.professionalCategoryService.getAll();
    }
    @Bindable
    public Listble getProfessionalCategory() {
        return this.professionalCategory;
    }
    public void setProfessionalCategory(Listble professionalCategory) {
        this.professionalCategory = (ProfessionalCategory) professionalCategory;
    }
    @Bindable
    public int getTrainingYear() {
        return this.employee.getTrainingYear();
    }
    public void setTrainingYear(int trainingYear) {
        this.employee.setTrainingYear(trainingYear);
    }
    @Bindable
    public String getPhoneNumber() {
        return this.employee.getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber) {
        this.employee.setPhoneNumber(phoneNumber);
    }
    @Bindable
    public String getEmail() {
        return this.employee.getEmail();
    }

    public void setEmail(String email) {
        this.employee.setEmail(email);
    }
    @Bindable
    public Listble getPartner() {
        return this.employee.getPartner();
    }
    public void setPartner(Partner partner) {
        this.employee.setPartner(partner);
    }

    public List<Tutored> getAllTutoreds() throws SQLException {
        return tutoredService.getAll();
    }

    public List<Province> getAllProvince() throws SQLException {
        return provinceService.getAll();
    }

    @Bindable
    public Listble getSelectedNgo() {
        return selectedNgo;
    }

    public void setSelectedNgo(Listble selectedNgo) {
        this.selectedNgo = (Partner) selectedNgo;
        notifyPropertyChanged(BR.selectedNgo);
    }

    private void doSave(){

          if(StringUtils.isEmpty(employee.getName())){
              Utilities.displayAlertDialog(getRelatedActivity(), "Campo nome nao pode estar vazio", getRelatedActivity()).show();
              return;
          }

          if(StringUtils.isEmpty(employee.getSurname())){
              Utilities.displayAlertDialog(getRelatedActivity(), "Campo sobrenome nao pode estar vazio", getRelatedActivity()).show();
              return;
          }

          if(StringUtils.isEmpty(employee.getPhoneNumber())){
              Utilities.displayAlertDialog(getRelatedActivity(), "Campo Telefone nao pode estar vazio", getRelatedActivity()).show();
              return;
          }

        if(StringUtils.isEmpty(employee.getEmail())){
            Utilities.displayAlertDialog(getRelatedActivity(), "Campo Email nao pode estar vazio", getRelatedActivity()).show();
            return;
        }
        if(this.getProfessionalCategory() == null){
            Utilities.displayAlertDialog(getRelatedActivity(), "Campo Categoria Professional nao pode estar vazio", getRelatedActivity()).show();
            return;
        }
        if(this.getProvince() == null){
            Utilities.displayAlertDialog(getRelatedActivity(), "Campo Provincia nao pode estar vazio", getRelatedActivity()).show();
            return;
        }
        /*
        if( (employee.getNuit() == 0 )){
            Utilities.displayAlertDialog(getRelatedActivity(), "Campo NUIT nao pode estar vazio", getRelatedActivity()).show();
            return;
        }

        if( (employee.getTrainingYear() == 0 )){
            Utilities.displayAlertDialog(getRelatedActivity(), "Campo Ano de Formacao nao pode estar vazio", getRelatedActivity()).show();
            return;
        }
    */
        employee.setProfessionalCategory((ProfessionalCategory) getProfessionalCategory());
        tutored.setUuid(Utilities.getNewUUID().toString());
        tutored.setEmployee(employee);
          employee.setUuid(Utilities.getNewUUID().toString());
          location.setUuid(Utilities.getNewUUID().toString());
          location.setEmployee(employee);
          location.setProvince((Province) getProvince());
          location.setDistrict((District) getDistrict());
          location.setHealthFacility((HealthFacility) getHealthFacility());
          location.setLocationLevel("N/A");

        location.setEmployee(employee);

        try {
            this.employeeService.saveOrUpdateEmployee(employee);
            this.tutoredService.savedOrUpdateTutored(tutored);
            this.locationService.saveOrUpdate(location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("createdTutored", tutored);
       getRelatedActivity().nextActivity(TutoredActivity.class, params);
    }
    public void save(){

            this.doSave();

    }



    @Bindable
    public Tutored getTutored() {
        return this.tutored;
    }

    public void setTutored(Tutored tutored) {
        this.tutored = tutored;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Bindable
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void deleteTutored(Tutored tutored) throws SQLException {
        this.tutoredService.delete(tutored);
    }

    public String tutoredHasSessions() {
        /*boolean hasSessions = false;
        try {
            hasSessions = this.episodeService.patientHasEndingEpisode(getDispense().getPrescription().getPatient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hasEndingEpisode) {
            return getRelatedActivity().getString(R.string.dispense_has_final_episode_cant_be_edit);
        }*/
        return "";
    }

    @Bindable
    public Listble getProvince() {
//        return this.location.getProvince();
        return this.province;
    }
    public void setProvince(Listble province) {
        //this.location.setProvince((Province) province);
        this.province =(Province) province;
        try {
        this.districts.clear();
        this.districts.addAll(this.districtService.getByProvince(this.province));
         getRelatedActivity().reloadDistrcitAdapter();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bindable
    public Listble getDistrict(){
//        return this.location.getDistrict();
        return this.district;
    }
    public void setDistrict(Listble district){
      //  this.location.setDistrict((District) district);
        try {
        this.district = (District) district;
        this.healthFacilities.clear();
            List<HealthFacility> healthFacilities1 = this.healthFacilityService.getHealthFacilityByDistrict(this.district);
            this.healthFacilities.addAll(healthFacilities1);
             getRelatedActivity().reloadHealthFacility();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Bindable
    public Listble getHealthFacility(){
      //  return this.location.getHealthFacility();
        return this.healthFacility;
    }

    public void setHealthFacility(Listble healthFacility){
        //this.location.setHealthFacility((HealthFacility) healthFacility);
        this.healthFacility = (HealthFacility) healthFacility;

    }

    @Bindable
    public boolean isInitialDataVisible() {
        return initialDataVisible;
    }

    public void setInitialDataVisible(boolean initialDataVisible) {
        this.initialDataVisible = initialDataVisible;
        this.notifyPropertyChanged(BR.initialDataVisible);
    }

    public void changeInitialDataViewStatus(View view){
        getRelatedActivity().changeFormSectionVisibility(view);
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<HealthFacility> getHealthFacilities() {
        return healthFacilities;
    }
    public void setHealthFacilities(List<HealthFacility> healthFacilities) {
        this.healthFacilities = healthFacilities;
    }

    private void loadMeteeLabors(){
        this.menteeLabors.add(SimpleValue.fastCreate("SNS"));
        this.menteeLabors.add(SimpleValue.fastCreate("ONG"));
    }
    public List<SimpleValue> getMenteeLabors() {
        return menteeLabors;
    }

    public void setMenteeLabors(List<SimpleValue> menteeLabors) {
        this.menteeLabors = menteeLabors;
    }

    @Bindable
    public Listble getMenteeLabor(){
        return Utilities.findOnArray(this.menteeLabors, SimpleValue.fastCreate("SNS"));
    }


    public void setMenteeLabor(Listble menteeLabor){
      SimpleValue selectSimpleValue = (SimpleValue) menteeLabor;
        if (selectSimpleValue.getDescription().equals("ONG")){
            getRelatedActivity().reloadVisibilityOngName(true);
        } else {
            getRelatedActivity().reloadVisibilityOngName(false);
        }
    }

    @Override
    public CreateTutoredActivity getRelatedActivity() {
        return (CreateTutoredActivity) super.getRelatedActivity();
    }

    public void createNewTutored() {
        getRelatedActivity().nextActivity(CreateTutoredActivity.class);
    }
    public List getAllPartners() {
        try {
            return getApplication().getPartnerService().getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
