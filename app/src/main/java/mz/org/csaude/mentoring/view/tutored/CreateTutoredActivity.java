package mz.org.csaude.mentoring.view.tutored;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityCreateTutoredBinding;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.service.career.CareerServiceImpl;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jose Julai Ritsure
 */
public class CreateTutoredActivity extends BaseActivity implements IDialogListener {
    private ActivityCreateTutoredBinding activityCreateTutoredBinding;
    private List<Tutored> tutoredList;
    private ListableSpinnerAdapter careerAdapter;
    private ListableSpinnerAdapter careerTypeAdapter;
    private TutoredService tutoredService;
    private CareerService careerService;
    private Tutored tutored;
    private Career selectedCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTutoredBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tutored);
        Intent intent = this.getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            //activityCreateTutoredBinding.setViewModel((TutoredVM)getRelatedViewModel());
        }
        tutoredService = new TutoredServiceImpl(getApplication(), getCurrentUser());
        careerService = new CareerServiceImpl(getApplication(), getCurrentUser());
        populateView();
        setPositions(0);

        activityCreateTutoredBinding.spnCareer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setPositions(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        activityCreateTutoredBinding.saveTutored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tutoredName = activityCreateTutoredBinding.tutoredName.getText().toString();
                if (StringUtils.isEmpty(tutoredName)) {
                    Utilities.validateTextField(getApplicationContext(), activityCreateTutoredBinding.tutoredName);
                    return;
                }
                String tutoredSurname = activityCreateTutoredBinding.tutoredSurname.getText().toString();
                if (StringUtils.isEmpty(tutoredSurname)) {
                    Utilities.validateTextField(getApplicationContext(), activityCreateTutoredBinding.tutoredSurname);
                    return;
                }
                String tutoredPhoneNumber = activityCreateTutoredBinding.tutoredPhoneNumber.getText().toString();
                if (StringUtils.isEmpty(tutoredPhoneNumber)) {
                    Utilities.validateTextField(getApplicationContext(), activityCreateTutoredBinding.tutoredPhoneNumber);
                    return;
                }
                if (!StringUtils.isEmpty(tutoredPhoneNumber)) {
                    if(Utilities.validadePhoneNumber(getApplicationContext(), activityCreateTutoredBinding.tutoredPhoneNumber))
                    {
                        return;
                    }
                }
                String tutoredEmail = activityCreateTutoredBinding.tutoredEmail.getText().toString();
                if (!StringUtils.isEmpty(tutoredEmail)) {
                    if(Utilities.validadeEmail(getApplicationContext(), activityCreateTutoredBinding.tutoredEmail))
                    {
                        return;
                    }
                }
                selectedCareer = (Career) activityCreateTutoredBinding.spnPosition.getSelectedItem();
                tutored = new Tutored(tutoredName, tutoredSurname, tutoredPhoneNumber, tutoredEmail, selectedCareer, getCurrentUser());
                tutored = getRelatedViewModel().save();
                Utilities.displayAlertDialog(getApplicationContext(), getString(R.string.record_successfully_saved)).show();
                Map<String, Object> params = new HashMap<>();
                params.put("createdTutored", tutored);
                nextActivity(TutoredActivity.class, params);
            }
        });
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

    private void populateView() {
        try {
            List<CareerType> careerTypes = getRelatedViewModel().getCareerTypes();
            careerTypeAdapter = new ListableSpinnerAdapter(CreateTutoredActivity.this, R.layout.simple_auto_complete_item, careerTypes);
            activityCreateTutoredBinding.spnCareer.setAdapter(careerTypeAdapter);
            activityCreateTutoredBinding.setCareerTypeAdapter(careerTypeAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPositions(int selectedCareerType){
        CareerType careerType = (CareerType) activityCreateTutoredBinding.spnCareer.getItemAtPosition(selectedCareerType);
        try {
            List<Career> careers = careerService.getCareersByCareerType(careerType);
            careerAdapter = new ListableSpinnerAdapter(CreateTutoredActivity.this, R.layout.simple_auto_complete_item, careers);
            activityCreateTutoredBinding.spnPosition.setAdapter(careerAdapter);
            activityCreateTutoredBinding.setCareerAdapter(careerAdapter);
            careerAdapter.notifyDataSetChanged();
            activityCreateTutoredBinding.getCareerAdapter().notifyDataSetChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TutoredVM getRelatedViewModel() {
        TutoredVM tutoredVM = (TutoredVM) super.getRelatedViewModel();
        return tutoredVM;
    }

}
