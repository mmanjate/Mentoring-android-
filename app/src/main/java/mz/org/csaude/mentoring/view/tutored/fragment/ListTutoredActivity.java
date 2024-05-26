package mz.org.csaude.mentoring.view.tutored.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.tutored.TutoredAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipTutoredsBinding;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

public class ListTutoredActivity extends BaseActivity {

    private ActivityMentorshipTutoredsBinding activityMentorshipTutoredsBinding;
    private RecyclerView rcvTutoreds;
    private TutoredAdapter tutoredItemAdapter;
    private Mentorship mentorship;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;
    private List<Tutored> mentees;
    private List<RondaMentee> selectedMentees;
    private RondaMentor rondaMentor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMentorshipTutoredsBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship_tutoreds);
        activityMentorshipTutoredsBinding.setViewModel(getRelatedViewModel());
        rcvTutoreds = activityMentorshipTutoredsBinding.rcvTutoreds;
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            mentorship = (Mentorship) intent.getExtras().get("newMentorship");
            ronda = (Ronda) intent.getExtras().get("createdRonda");
            title = (String) intent.getExtras().get("title");
            rondaType = (RondaType) intent.getExtras().get("rondaType");
            currMentor = (Tutor) intent.getExtras().get("currMentor");
            this.selectedMentees = (List<RondaMentee>) intent.getExtras().getSerializable("rondaMentees");
            rondaMentor = (RondaMentor) intent.getExtras().get("rondaMentor");
            bundle.putSerializable("ronda", ronda);
            bundle.putSerializable("title", title);
            bundle.putSerializable("rondaType", rondaType);
            bundle.putSerializable("currMentor", currMentor);
            bundle.putSerializable("rondaMentees", (Serializable) selectedMentees);
            bundle.putSerializable("rondaMentor", rondaMentor);
            intent.putExtras(bundle);
        }
        initTutoredList();
    }

    private void initTutoredList() {
        if (Utilities.listHasElements(this.selectedMentees)) {
            List<Tutored> mentees = new ArrayList<>();
            for (RondaMentee rondaMentee: selectedMentees) {
                mentees.add(rondaMentee.getTutored());
            }
            this.tutoredItemAdapter = new TutoredAdapter(rcvTutoreds, mentees, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            rcvTutoreds.setLayoutManager(mLayoutManager);
            rcvTutoreds.setItemAnimator(new DefaultItemAnimator());
            rcvTutoreds.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
            rcvTutoreds.setAdapter(tutoredItemAdapter);
        }
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
