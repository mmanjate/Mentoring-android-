package mz.org.csaude.mentoring.view.mentorship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipVM;
import mz.org.csaude.mentoring.viewmodel.tutor.TutorVM;

public class MentorshipActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentorship);
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(MentorshipVM.class);
    }

    @Override
    public MentorshipVM getRelatedViewModel() {
        return (MentorshipVM) super.getRelatedViewModel();
    }
}