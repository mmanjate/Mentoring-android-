package mz.org.csaude.mentoring.view.career;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityCareerBinding;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.viewmodel.career.CareerVM;

public class CareerActivity extends BaseActivity {

    ActivityCareerBinding careerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careerBinding = DataBindingUtil.setContentView(this, R.layout.activity_career);

        careerBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(CareerVM.class);
    }

    @Override
    public CareerVM getRelatedViewModel() {
      return (CareerVM)  super.getRelatedViewModel();
    }
}
