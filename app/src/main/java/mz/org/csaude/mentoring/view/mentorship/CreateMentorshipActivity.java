package mz.org.csaude.mentoring.view.mentorship;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.form.FormAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipBinding;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipVM;

public class CreateMentorshipActivity extends BaseActivity {

    private ActivityMentorshipBinding mentorshipBinding;

    private RecyclerView formsRcv;

    private FormAdapter formAdapter;
    private Mentorship mentorship;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;
    private List<Form> forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mentorshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship);
        mentorshipBinding.setViewModel(getRelatedViewModel());
        formsRcv = mentorshipBinding.rcvForms;
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            mentorship = (Mentorship) intent.getExtras().get("newMentorship");
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
        initMentorShip();
    }

    private void initMentorShip() {
        getRelatedViewModel().loadTutorForms(this.currMentor);
        forms = getRelatedViewModel().getTutorForms();

        if (Utilities.listHasElements(forms)) {
            this.formAdapter = new FormAdapter(forms);
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
