package mz.org.csaude.mentoring.view.ronda;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.tutored.TutoredAdapter;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityRondaBinding;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaSearchVM;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaVM;

public class RondaActivity extends BaseActivity {

    private ActivityRondaBinding rondaBinding;

    private ListableSpinnerAdapter districtAdapter;
    private ListableSpinnerAdapter provinceAdapter;
    private ListableSpinnerAdapter healthFacilityAdapter;

    private TutoredAdapter tutoredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        rondaBinding = DataBindingUtil.setContentView(this, R.layout.activity_ronda);
        rondaBinding.setViewModel(getRelatedViewModel());
        setContentView(R.layout.activity_ronda);


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
        ArrayAdapter<Tutored> drugArrayAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getMentees());
        rondaBinding.autCmpMentees.setThreshold(1);
        rondaBinding.autCmpMentees.setAdapter(drugArrayAdapter);
    }

    public void reloadDistrcitAdapter() {
        districtAdapter = new ListableSpinnerAdapter(RondaActivity.this, R.layout.simple_auto_complete_item, getRelatedViewModel().getDistricts());
        rondaBinding.spnDistrict.setAdapter(districtAdapter);
        rondaBinding.setDistrictAdapter(districtAdapter);
    }
}