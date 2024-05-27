package mz.org.csaude.mentoring.view.mentorship;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityListMentorshipBinding;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipSearchVM;

import java.io.Serializable;
import java.util.List;

public class MentorshipActivity extends BaseActivity {

    private ActivityListMentorshipBinding binding;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;
    private List<RondaMentee> selectedMentees;
    private RondaMentor rondaMentor;

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
            selectedMentees = (List<RondaMentee>) intent.getExtras().getSerializable("rondaMentees");
            rondaMentor = (RondaMentor) intent.getExtras().get("rondaMentor");
            bundle.putSerializable("ronda", ronda);
            bundle.putSerializable("title", title);
            bundle.putSerializable("rondaType", rondaType);
            bundle.putSerializable("currMentor", currMentor);
            bundle.putSerializable("rondaMentees", (Serializable) selectedMentees);
            bundle.putSerializable("rondaMentor", rondaMentor);
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
