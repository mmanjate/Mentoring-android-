package mz.org.csaude.mentoring.view.tutored;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityCreateTutoredBinding;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jose Julai Ritsure
 */
public class CreateTutoredActivity extends BaseActivity implements IDialogListener {
    private ActivityCreateTutoredBinding activityCreateTutoredBinding;
    private ListableSpinnerAdapter provinceAdapter;
    private ListableSpinnerAdapter districtAdapter;
    private ListableSpinnerAdapter healthfacilityAdapter;
    private ListableSpinnerAdapter professionalCategoryAdapter;

    private ListableSpinnerAdapter ngoAdapter;
    private ListableSpinnerAdapter menteeLaborfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTutoredBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tutored);
        activityCreateTutoredBinding.setViewModel(getRelatedViewModel());
        /*activityCreateTutoredBinding.laboralLyt.setVisibility(View.GONE);
        activityCreateTutoredBinding.healtUnitLyt.setVisibility(View.GONE);
        activityCreateTutoredBinding.spnNgo.setVisibility(View.GONE);*/

        Intent intent = this.getIntent();
        if (intent != null) {

           // getRelatedViewModel().setTutored((Tutored) bundle.ge);

            Bundle bundle = intent.getExtras();
            getRelatedViewModel().setInitialDataVisible(true);
        }
        initAdapters();

    }

    private void switchLayout(){
        getRelatedViewModel().setInitialDataVisible(!getRelatedViewModel().isInitialDataVisible());

    }

    private void initAdapters(){

        try {
            List<Province> provinces = getRelatedViewModel().getAllProvince();
            provinceAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, provinces);
            activityCreateTutoredBinding.spnProvince.setAdapter(provinceAdapter);
            activityCreateTutoredBinding.setProvinceAdapter(provinceAdapter);

            professionalCategoryAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getAllProfessionalCategys());
            activityCreateTutoredBinding.spnProfessionalCategory.setAdapter(professionalCategoryAdapter);
            activityCreateTutoredBinding.setProfessionalCategoryAdapter(professionalCategoryAdapter);

            menteeLaborfoAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getMenteeLabors());
            activityCreateTutoredBinding.spnMenteeLaborInfo.setAdapter(menteeLaborfoAdapter);
            activityCreateTutoredBinding.setMenteeLaborfoAdapter(menteeLaborfoAdapter);

            ngoAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getAllPartners());
            activityCreateTutoredBinding.setNgoAdapter(ngoAdapter);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(TutoredVM.class);
    }

    @Override
    public void doOnConfirmed() {

    }

    @Override
    public void doOnDeny() {
    }

    @Override
    public TutoredVM getRelatedViewModel() {
        return (TutoredVM) super.getRelatedViewModel();
    }

    public void reloadDistrcitAdapter() {
        districtAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getDistricts());
        activityCreateTutoredBinding.spnDistrict.setAdapter(districtAdapter);
        activityCreateTutoredBinding.setDistrictAdapter(districtAdapter);
    }

    public void reloadHealthFacility(){
        healthfacilityAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getHealthFacilities());
        activityCreateTutoredBinding.spnHealthfacility.setAdapter(healthfacilityAdapter);
        activityCreateTutoredBinding.setHealthfacilityAdapter(healthfacilityAdapter);

    }

    public void changeFormSectionVisibility(View view){

        if(view.equals(activityCreateTutoredBinding.laboralData)){
            if(activityCreateTutoredBinding.laboralLyt.getVisibility() == View.VISIBLE){
                activityCreateTutoredBinding.btnLaboralData.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(activityCreateTutoredBinding.laboralLyt);
            } else {
                switchLayout();
                Utilities.expand(activityCreateTutoredBinding.laboralLyt);
                activityCreateTutoredBinding.btnLaboralData.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }

        } else if(view.equals(activityCreateTutoredBinding.healtUnit)){
            if(activityCreateTutoredBinding.healtUnitLyt.getVisibility() == View.VISIBLE){
                activityCreateTutoredBinding.btnHealtUnit.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(activityCreateTutoredBinding.healtUnitLyt);
            } else {
                switchLayout();
                Utilities.expand(activityCreateTutoredBinding.healtUnitLyt);
                activityCreateTutoredBinding.btnHealtUnit.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }

        }  else if(view.equals(activityCreateTutoredBinding.identificationData)){
            if(activityCreateTutoredBinding.identificationDataLyt.getVisibility() == View.VISIBLE){
                switchLayout();
                Utilities.collapse(activityCreateTutoredBinding.identificationDataLyt);
                activityCreateTutoredBinding.btnIdentificationData.setImageResource(R.drawable.sharp_arrow_drop_up_24);
            } else {
                switchLayout();
                Utilities.expand(activityCreateTutoredBinding.identificationDataLyt);
                activityCreateTutoredBinding.btnIdentificationData.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }
        } else if(view.equals(activityCreateTutoredBinding.spnMenteeLaborInfo)){
            if (activityCreateTutoredBinding.spnMenteeLaborInfo.getSelectedItem() == "ONG"){
                activityCreateTutoredBinding.spnNgo.setVisibility(View.VISIBLE);
            }else{
                activityCreateTutoredBinding.spnNgo.setVisibility(View.GONE);
            }
        }
    }
}
