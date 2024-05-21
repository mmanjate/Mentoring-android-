package mz.org.csaude.mentoring.view.form;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.form.FormAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityListFormBinding;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class ListFormActivity extends BaseActivity {
    private ActivityListFormBinding activityListFormBinding;
    private RecyclerView formsRcv;
    private FormAdapter formAdapter;
    private Mentorship mentorship;
    private Ronda ronda;
    private String title;
    private RondaType rondaType;
    private Tutor currMentor;
    private List<Form> forms;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListFormBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_form);
        formsRcv = activityListFormBinding.rcvForms;
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            mentorship = (Mentorship) intent.getExtras().get("newMentorship");
            ronda = (Ronda) intent.getExtras().get("createdRonda");
            title = (String) intent.getExtras().get("title");
            rondaType = (RondaType) intent.getExtras().get("rondaType");
            currMentor = (Tutor) intent.getExtras().get("currMentor");
        }
    }

    @Override
    public BaseViewModel initViewModel() {
        return null;
    }
}
