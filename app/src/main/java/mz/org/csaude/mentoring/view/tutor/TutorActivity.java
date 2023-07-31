package mz.org.csaude.mentoring.view.tutor;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityTutorBinding;
import mz.org.csaude.mentoring.databinding.ActivityTutoredBinding;
import mz.org.csaude.mentoring.viewmodel.tutor.TutorVM;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

public class TutorActivity extends BaseActivity {
    ActivityTutorBinding tutorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutorBinding = DataBindingUtil.setContentView(this, R.layout.activity_tutor);

        tutorBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(TutorVM.class);
    }

    @Override
    public TutorVM getRelatedViewModel() {
        return (TutorVM) super.getRelatedViewModel();
    }
}
