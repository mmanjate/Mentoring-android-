package mz.org.csaude.mentoring.view.mentorship;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityListMentorshipBinding;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipSearchVM;

import android.content.Intent;
import android.os.Bundle;

public class MentorshipActivity extends BaseActivity {

    private ActivityListMentorshipBinding binding;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_mentorship);
        binding.setViewModel(getRelatedViewModel());

        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            ronda = (Ronda) intent.getExtras().get("createdRonda");
            title = (String) intent.getExtras().get("title");
            rondaType = (RondaType) intent.getExtras().get("rondaType");
            currMentor = (Tutor) intent.getExtras().get("currMentor");
            bundle.putSerializable("ronda", ronda);
            bundle.putSerializable("title", title);
            bundle.putSerializable("rondaType", rondaType);
            bundle.putSerializable("currMentor", currMentor);
            intent.putExtras(bundle);
        }
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