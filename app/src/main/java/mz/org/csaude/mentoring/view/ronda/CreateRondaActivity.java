package mz.org.csaude.mentoring.view.ronda;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.ListbleRecycleViewAdapter;
import mz.org.csaude.mentoring.adapter.recyclerview.tutored.TutoredAdapter;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityRondaBinding;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.util.RondaType;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaVM;

public class CreateRondaActivity extends BaseActivity {
    private ActivityRondaBinding rondaBinding;
    private ListableSpinnerAdapter districtAdapter;
    private ListableSpinnerAdapter provinceAdapter;
    private ListableSpinnerAdapter healthFacilityAdapter;
    private RecyclerView rcvSelectedMentees;
    private TutoredAdapter tutoredAdapter;
    private Ronda ronda;
    private String title;
    private mz.org.csaude.mentoring.model.rondatype.RondaType rondaType;
    private RondaType rondaTypeOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        rondaBinding = DataBindingUtil.setContentView(this, R.layout.activity_ronda);
        rondaBinding.setViewModel(getRelatedViewModel());


        ArrayAdapter<Tutored> menteesAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getMentees());
        rondaBinding.autCmpMentees.setThreshold(1);
        rondaBinding.autCmpMentees.setAdapter(menteesAdapter);
        rondaBinding.autCmpMentees.setOnFocusChangeListener((view, b) -> {
            rondaBinding.autCmpMentees.showDropDown();
        });



        rcvSelectedMentees = rondaBinding.rcvSelectedMentees;
        setSupportActionBar(rondaBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            ronda = (Ronda) intent.getExtras().get("createdRonda");
            title = (String) intent.getExtras().get("title");
            rondaTypeOption = (RondaType) intent.getExtras().get("rondaType");
            bundle.putSerializable("ronda", ronda);
            bundle.putSerializable("title", title);
            bundle.putSerializable("rondaType", rondaTypeOption);
            intent.putExtras(bundle);
        }

        initAdapters();
        rondaBinding.prescriptionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateRondaActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        getRelatedViewModel().setStartDate(DateUtilities.createDate(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, DateUtilities.DATE_FORMAT));
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        rondaBinding.autCmpMentees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                getRelatedViewModel().setSelectedMentee((Tutored) adapterView.getItemAtPosition(pos));
                rondaBinding.autCmpMentees.dismissDropDown();
                Utilities.hideKeyboard(CreateRondaActivity.this);
            }
        });

        //loadSelectedMenteeToForm();

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(RondaVM.class);
    }

    @Override
    public RondaVM getRelatedViewModel() {
        return (RondaVM) super.getRelatedViewModel();
    }

    public void reloadMenteesAdapter() {
        List<Tutored> tutoredList = getRelatedViewModel().getMentees();
        ArrayAdapter<Tutored> drugArrayAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, tutoredList);
        rondaBinding.autCmpMentees.setThreshold(1);
        rondaBinding.autCmpMentees.setAdapter(drugArrayAdapter);
    }

    public void reloadDistrictAdapter() {
        districtAdapter = new ListableSpinnerAdapter(CreateRondaActivity.this, R.layout.simple_auto_complete_item, getRelatedViewModel().getDistricts());
        rondaBinding.spnDistrict.setAdapter(districtAdapter);
        rondaBinding.setDistrictAdapter(districtAdapter);
    }

    public void reloadHealthFacility(){
        healthFacilityAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getHealthFacilities());
        rondaBinding.spnHealthFacility.setAdapter(healthFacilityAdapter);
        rondaBinding.setHealthFacilityAdapter(healthFacilityAdapter);

    }

    private void initAdapters(){
        try {
            List<Province> provinces = getRelatedViewModel().getAllProvince();
            provinceAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, provinces);
            rondaBinding.spnProvince.setAdapter(provinceAdapter);
            rondaBinding.setProvinceAdapter(provinceAdapter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void displaySelectedMentees(){
        if (tutoredAdapter != null) {
            tutoredAdapter.notifyDataSetChanged();
        }else {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rcvSelectedMentees.setLayoutManager(mLayoutManager);
            rcvSelectedMentees.setItemAnimator(new DefaultItemAnimator());
            rcvSelectedMentees.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));

            tutoredAdapter = new TutoredAdapter(rcvSelectedMentees, getRelatedViewModel().getSelectedMentees(), this);
            rcvSelectedMentees.setAdapter(tutoredAdapter);
        }
    }
    private void loadSelectedMenteeToForm() {
        if (getRelatedViewModel().getSelectedMentees() == null) getRelatedViewModel().setSelectedMentees(new ArrayList<>());
        for (Tutored tutored : getRelatedViewModel().getMentees()){
            getRelatedViewModel().getSelectedMentees().add(tutored);
        }
        displaySelectedMentees();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button click
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}