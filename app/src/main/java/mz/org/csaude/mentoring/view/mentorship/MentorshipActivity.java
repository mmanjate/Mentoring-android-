package mz.org.csaude.mentoring.view.mentorship;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityListMentorshipBinding;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipSearchVM;

import android.os.Bundle;

public class MentorshipActivity extends BaseActivity {

    private ActivityListMentorshipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_mentorship);
        binding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(MentorshipSearchVM.class);
    }

    @Override
    public MentorshipSearchVM getRelatedViewModel() {
        return (MentorshipSearchVM) super.getRelatedViewModel();
    }
}