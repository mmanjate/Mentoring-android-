package mz.org.csaude.mentoring.view.question;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.question.QuestionAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipQuestionsBinding;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaMentor;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.viewmodel.question.QuestionVM;

public class ListQuestionActivity extends BaseActivity {
    private ActivityMentorshipQuestionsBinding activityMentorshipQuestionsBinding;
    private List<Question> questions;
    private List<FormQuestion> formQuestions;
    private RecyclerView rcvQuestions;
    private QuestionAdapter questionAdapter;
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
        activityMentorshipQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship_questions);
        rcvQuestions = activityMentorshipQuestionsBinding.rcvQuestions;
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
    }
    private void initQuestionsList() {

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(QuestionVM.class);
    }

    @Override
    public QuestionVM getRelatedViewModel() {
        return (QuestionVM) super.getRelatedViewModel();
    }
}
