package mz.org.csaude.mentoring.view.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityCreateTutorBinding;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.home.ui.personalinfo.MentorVM;

public class CreateTutorActivity extends BaseActivity implements IDialogListener {

    private ActivityCreateTutorBinding activityCrceateTutorBinding;

    private ListableSpinnerAdapter provinceAdapter;
    private ListableSpinnerAdapter districtAdapter;
    private ListableSpinnerAdapter healthfacilityAdapter;
    private ListableSpinnerAdapter professionalCategoryAdapter;

    private ListableSpinnerAdapter ngoAdapter;
    private ListableSpinnerAdapter menteeLaborfoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCrceateTutorBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tutor);
        activityCrceateTutorBinding.setViewModel(getRelatedViewModel());

        Intent intent = this.getIntent();
        if (intent != null) {

            Bundle bundle = intent.getExtras();
            getRelatedViewModel().setInitialDataVisible(true);
        }
        initAdapters();
    }


    @Override
    public void doOnConfirmed() {

    }

    @Override
    public void doOnDeny() {

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(MentorVM.class);
    }

    @Override
    public MentorVM getRelatedViewModel(){
        return (MentorVM) super.getRelatedViewModel();
    }


    private void switchLayout(){
        getRelatedViewModel().setInitialDataVisible(!getRelatedViewModel().isInitialDataVisible());

    }

    private void initAdapters(){

        try {
            List<Province> provinces = getRelatedViewModel().getAllProvince();
            provinceAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, provinces);
            activityCrceateTutorBinding.spnProvince.setAdapter(provinceAdapter);
            activityCrceateTutorBinding.setProvinceAdapter(provinceAdapter);

            professionalCategoryAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getAllProfessionalCategys());
            activityCrceateTutorBinding.spnProfessionalCategory.setAdapter(professionalCategoryAdapter);
            activityCrceateTutorBinding.setProfessionalCategoryAdapter(professionalCategoryAdapter);

            menteeLaborfoAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getMenteeLabors());
            activityCrceateTutorBinding.spnMenteeLaborInfo.setAdapter(menteeLaborfoAdapter);
            activityCrceateTutorBinding.setMenteeLaborfoAdapter(menteeLaborfoAdapter);

            ngoAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getAllPartners());
            activityCrceateTutorBinding.spnNgo.setAdapter(ngoAdapter);
            activityCrceateTutorBinding.setNgoAdapter(ngoAdapter);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadDistrcitAdapter() {
        districtAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getDistricts());
        activityCrceateTutorBinding.spnDistrict.setAdapter(districtAdapter);
        activityCrceateTutorBinding.setDistrictAdapter(districtAdapter);
    }

    public void reloadHealthFacility(){
        healthfacilityAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getHealthFacilities());
        activityCrceateTutorBinding.spnHealthfacility.setAdapter(healthfacilityAdapter);
        activityCrceateTutorBinding.setHealthfacilityAdapter(healthfacilityAdapter);

    }
    public void changeFormSectionVisibility(View view){

        if(view.equals(activityCrceateTutorBinding.laboralData)){
            if(activityCrceateTutorBinding.laboralLyt.getVisibility() == View.VISIBLE){
                activityCrceateTutorBinding.btnLaboralData.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(activityCrceateTutorBinding.laboralLyt);
            } else {
                switchLayout();
                Utilities.expand(activityCrceateTutorBinding.laboralLyt);
                activityCrceateTutorBinding.btnLaboralData.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }

        } else if(view.equals(activityCrceateTutorBinding.healtUnit)){
            if(activityCrceateTutorBinding.healtUnitLyt.getVisibility() == View.VISIBLE){
                activityCrceateTutorBinding.btnHealtUnit.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(activityCrceateTutorBinding.healtUnitLyt);
            } else {
                switchLayout();
                Utilities.expand(activityCrceateTutorBinding.healtUnitLyt);
                activityCrceateTutorBinding.btnHealtUnit.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }

        }  else if(view.equals(activityCrceateTutorBinding.identificationData)){
            if(activityCrceateTutorBinding.identificationDataLyt.getVisibility() == View.VISIBLE){
                switchLayout();
                Utilities.collapse(activityCrceateTutorBinding.identificationDataLyt);
                activityCrceateTutorBinding.btnIdentificationData.setImageResource(R.drawable.sharp_arrow_drop_up_24);
            } else {
                switchLayout();
                Utilities.expand(activityCrceateTutorBinding.identificationDataLyt);
                activityCrceateTutorBinding.btnIdentificationData.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }
        } else if(view.equals(activityCrceateTutorBinding.spnMenteeLaborInfo)){
            if (activityCrceateTutorBinding.spnMenteeLaborInfo.getSelectedItem() == "ONG"){
                activityCrceateTutorBinding.spnNgo.setVisibility(View.VISIBLE);
            }else{
                activityCrceateTutorBinding.spnNgo.setVisibility(View.GONE);
            }
        }
    }
}