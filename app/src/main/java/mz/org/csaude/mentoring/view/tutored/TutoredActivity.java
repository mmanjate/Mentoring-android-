package mz.org.csaude.mentoring.view.tutored;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityTutoredBinding;
import mz.org.csaude.mentoring.viewmodel.home.HomeVM;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

public class TutoredActivity extends BaseActivity {

    ActivityTutoredBinding tutoredBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutoredBinding = DataBindingUtil.setContentView(this, R.layout.activity_tutored);

        tutoredBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(TutoredVM.class);
    }

    @Override
    public TutoredVM getRelatedViewModel() {
        return (TutoredVM) super.getRelatedViewModel();
    }
}