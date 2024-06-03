package mz.org.csaude.mentoring.view.form;

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
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.form.FormAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipFormsBinding;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.form.FormVM;

public class ListFormActivity extends BaseActivity {
    private ActivityMentorshipFormsBinding activityMentorshipFormsBinding;
    private RecyclerView formsRcv;
    private FormAdapter formAdapter;
    private Mentorship mentorship;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;
    private List<Form> forms;
    private List<RondaMentee> selectedMentees;
    private RondaMentor rondaMentor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMentorshipFormsBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship_forms);
        activityMentorshipFormsBinding.setViewModel(getRelatedViewModel());
        formsRcv = activityMentorshipFormsBinding.rcvForms;
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            mentorship = (Mentorship) intent.getExtras().get("newMentorship");
            getRelatedViewModel().setMentorship(mentorship);
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
        initFormActivity();
    }
    private void initFormActivity() {
        getRelatedViewModel().loadTutorForms(this.currMentor);
        forms = getRelatedViewModel().getTutorForms();

        if (Utilities.listHasElements(forms)) {
            this.formAdapter = new FormAdapter(formsRcv, forms, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            formsRcv.setLayoutManager(mLayoutManager);
            formsRcv.setItemAnimator(new DefaultItemAnimator());
            formsRcv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
            formsRcv.setAdapter(formAdapter);
        }

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(FormVM.class);
    }
    @Override
    public FormVM getRelatedViewModel() {
        return (FormVM) super.getRelatedViewModel();
    }
}
