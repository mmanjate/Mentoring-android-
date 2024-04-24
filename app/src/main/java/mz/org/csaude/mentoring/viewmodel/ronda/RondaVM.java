package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.service.ronda.RondaTypeService;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.mentorship.MentorshipActivity;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.tutored.CreateTutoredActivity;
import mz.org.csaude.mentoring.view.tutored.TutoredActivity;

public class RondaVM extends BaseViewModel {
    private RondaService rondaService;
    private RondaTypeService rondaTypeService;
    private Ronda ronda;
    private Province selectedProvince;
    private District selectedDistrict;
    private HealthFacility selectedHealthFacility;
    private Province province;
    private List<Province> provinces;
    private List<District> districts;
    private List<HealthFacility> healthFacilities;
    private Tutored selectedMentee;
    private List<Tutored> menteeList;

    private List<Tutored> selectedMentees;

    public RondaVM(@NonNull Application application) {
        super(application);
        this.ronda = new Ronda();
        rondaService = getApplication().getRondaService();
        rondaTypeService = getApplication().getRondaTypeService();
    }

    @Override
    public CreateRondaActivity getRelatedActivity() {
        return (CreateRondaActivity) super.getRelatedActivity();
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

    public void setHealthFacility(Listble selectedHealthFacility) {
        this.selectedHealthFacility = (HealthFacility) selectedHealthFacility;
        try {
            if(this.menteeList!=null) {
                this.menteeList.clear();
            }
            else {
                this.menteeList = new ArrayList<>();
            }
            if(!StringUtils.isEmpty(this.selectedHealthFacility.getUuid())) {
                this.ronda.setHealthFacility(this.selectedHealthFacility);
                List<Tutored> tutoredList = getApplication().getTutoredService().getAllOfHealthFacility(this.selectedHealthFacility);
                setMenteeList(tutoredList);
            }
            getRelatedActivity().reloadMenteesAdapter();
            notifyPropertyChanged(BR.healthFacility);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMenteeList(List<Tutored> menteeList) {
        this.menteeList = menteeList;
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
            if(this.districts!=null) {
                this.districts.clear();
            }
            else {
                this.districts = new ArrayList<>();
            }
            if(this.healthFacilities!=null) {
                this.healthFacilities.clear();
            }
            else {
                this.healthFacilities = new ArrayList<>();
            }
            districts.add(new District());
            if(!StringUtils.isEmpty(this.selectedProvince.getUuid())) {
                districts.addAll(getApplication().getDistrictService().getByProvince(this.selectedProvince));
            }
            getRelatedActivity().reloadDistrictAdapter();
            notifyPropertyChanged(BR.selectedProvince);
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
        try {
            if(this.healthFacilities!=null) {
                this.healthFacilities.clear();
            }
            else {
                this.healthFacilities = new ArrayList<>();
            }
        this.healthFacilities.add(new HealthFacility());
            if(!StringUtils.isEmpty(this.selectedDistrict.getUuid())) {
                this.healthFacilities.addAll(getApplication().getHealthFacilityService().getHealthFacilityByDistrict(this.selectedDistrict));
            }
        getRelatedActivity().reloadHealthFacility();
        notifyPropertyChanged(BR.selectedDistrict);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        // validate mentee here...

        if (selectedMentees == null) selectedMentees = new ArrayList<>();
        this.setSelectedMentees(menteeList);
        //if(selectedMentee != null){
            //if (!selectedMentees.contains(selectedMentee)) {
                //selectedMentee.setListPosition(selectedMentees.size()+1);
                //selectedMentee.setListType(Listble.PRESCRIPTION_DRUG_LISTING);
                //selectedMentees.add(selectedMentee);
                ((CreateRondaActivity) getRelatedActivity()).displaySelectedMentees();
                setSelectedMentee(null);
                notifyPropertyChanged(BR.selectedMentee);
            /*}else {
                Utilities.displayAlertDialog(getRelatedActivity(), "O Mentorando seleccionado já existe na lista!").show();
            }
        }else{
            Utilities.displayAlertDialog(getRelatedActivity(),"Campo Mentorando está vazio. Por favor, seleccione um medicamento para adicionar à lista.").show();
        }*/
    }

    public void save() {
        try {
            ronda.setSyncStatus(SyncSatus.PENDING);
            ronda.setUuid(Utilities.getNewUUID().toString());
            RondaType rondaType = this.rondaTypeService.getRondaTypeByCode("SESSAO_ZERO");
            ronda.setRondaType(rondaType);
            ronda.setStartDate(this.getStartDate());
            ronda.setHealthFacility(this.selectedHealthFacility);
            int count = getApplication().getRondaService().countRondas();
            count++;
            ronda.setDescription(rondaType.getDescription()+" "+count);
            String error = this.ronda.validade();
            if (Utilities.stringHasValue(error)) {
                Utilities.displayAlertDialog(getRelatedActivity(), error).show();
                return;
            }
            Ronda createdRonda = getApplication().getRondaService().savedOrUpdateRonda(ronda);
            List<RondaMentee> rondaMentees = new ArrayList<>();
            for (Tutored tutored : this.getSelectedMentees()) {
                RondaMentee rondaMentee = new RondaMentee();
                rondaMentee.setUuid(Utilities.getNewUUID().toString());
                rondaMentee.setRonda(createdRonda);
                rondaMentee.setTutored(tutored);
                rondaMentee.setStartDate(this.getStartDate());
                RondaMentee createdRondaMentee = getApplication().getRondaMenteeService().savedOrUpdateRondaMentee(rondaMentee);
                rondaMentees.add(createdRondaMentee);
            }

            List<RondaMentor> rondaMentors = new ArrayList<>();
            Tutor tutor = this.getApplication().getCurrMentor();
            RondaMentor rondaMentor = new RondaMentor();
            rondaMentor.setUuid(Utilities.getNewUUID().toString());
            rondaMentor.setRonda(createdRonda);
            rondaMentor.setTutor(tutor);
            rondaMentor.setStartDate(this.getStartDate());
            RondaMentor createdRondaMentor = getApplication().getRondaMentorService().savedOrUpdateRondaMentor(rondaMentor);
            rondaMentors.add(createdRondaMentor);
            Map<String, Object> params = new HashMap<>();
            params.put("createdRonda", createdRonda);
            params.put("rondaMentees", rondaMentees);
            params.put("rondaMentor", rondaMentor);
            getRelatedActivity().nextActivity(MentorshipActivity.class, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tutored> getMentees() {
        return this.menteeList;
    }

    public List<Province> getAllProvince() throws SQLException {
        List<Province> provinceList = new ArrayList<>();
        provinceList.add(new Province());
        provinceList.addAll(getApplication().getProvinceService().getAllOfTutor(getApplication().getCurrMentor()));
        return provinceList;
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
    public void edit(Ronda ronda) {
        Map<String, Object> params = new HashMap<>();
        params.put("relatedRecord", ronda);
        getCurrentStep().changeToEdit();
        getRelatedActivity().nextActivity(CreateRondaActivity.class, params);
    }

    public void setSelectedMentees(List<Tutored> mentees) {
        this.selectedMentees = mentees;
    }

    public List<Tutored> getSelectedMentees() {
        return this.selectedMentees;
    }
    private boolean validateRondaMentee(Tutored selectedRondaMentee) {
        // TODO
        return true;
    }
    public void createNewRonda() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(CreateRondaActivity.class, params);
    }
}
