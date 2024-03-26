package mz.org.csaude.mentoring.view.mentorship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.form.FormAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipBinding;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipVM;
import mz.org.csaude.mentoring.viewmodel.tutor.TutorVM;

public class MentorshipActivity extends BaseActivity {

    private ActivityMentorshipBinding mentorshipBinding;

    private RecyclerView formsRcv;

    private FormAdapter formAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mentorshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship);
        initMentorShip();
    }

    private void initMentorShip() {
        getRelatedViewModel().loadTutorForms();

        if (Utilities.listHasElements(getRelatedViewModel().getTutorForms())) {
            this.formAdapter = new FormAdapter(getRelatedViewModel().getTutorForms());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            formsRcv.setLayoutManager(mLayoutManager);
            formsRcv.setItemAnimator(new DefaultItemAnimator());
            formsRcv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
            formsRcv.setAdapter(formAdapter);
        }

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