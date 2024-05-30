package mz.org.csaude.mentoring.view.mentorship;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.form.FormAdapter;
import mz.org.csaude.mentoring.adapter.recyclerview.question.QuestionAdapter;
import mz.org.csaude.mentoring.adapter.recyclerview.tutored.TutoredAdapter;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentorshipBinding;
import mz.org.csaude.mentoring.listner.recyclerView.ClickListener;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipVM;

public class CreateMentorshipActivity extends BaseActivity implements ClickListener.OnItemClickListener {

    private ActivityMentorshipBinding mentorshipBinding;

    private RecyclerView formsRcv;

    private FormAdapter formAdapter;

    private TutoredAdapter tutoredAdapter;

    private ListableSpinnerAdapter sectorAdapter;

    private ListableSpinnerAdapter doorAdapter;

    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mentorshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentorship);
        formsRcv = mentorshipBinding.rcvForms;
        Intent intent = this.getIntent();
        if(intent!=null && intent.getExtras()!=null) {
            getRelatedViewModel().setSession((Session) intent.getExtras().get("session"));
            getRelatedViewModel().setRonda((Ronda) intent.getExtras().get("ronda"));
            getRelatedViewModel().setCurrMentorshipStep((String) intent.getExtras().get("CURR_MENTORSHIP_STEP"));
            getRelatedViewModel().determineMentorshipType();
            populateFormList();
        }

        setSupportActionBar(mentorshipBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getRelatedViewModel().isMentoringMentorship() ? "Avaliação" : "Sessão Zero");

        mentorshipBinding.setViewModel(getRelatedViewModel());
        loadSectorAdapter();
        loadDoorAdapter();

        mentorshipBinding.sessionDate.setOnClickListener(view -> {
            int mYear, mMonth, mDay;

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateMentorshipActivity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    getRelatedViewModel().setStartDate(DateUtilities.createDate(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, DateUtilities.DATE_FORMAT));
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mentorshipBinding.sessionStartTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog(mentorshipBinding.sessionStartTime);
                }
            }
        });
        mentorshipBinding.sessionEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog(mentorshipBinding.sessionEndTime);
                }
            }
        });
    }

    private void showTimePickerDialog(EditText viewTe) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Show the time picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Handle the time set event
                String time = hourOfDay + ":" + minute;
                viewTe.setText(time);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void populateFormList() {
        this.formAdapter = new FormAdapter(formsRcv, getRelatedViewModel().getTutorForms(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        formsRcv.setLayoutManager(mLayoutManager);
        formsRcv.setItemAnimator(new DefaultItemAnimator());
        formsRcv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
        formsRcv.setAdapter(formAdapter);
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(MentorshipVM.class);
    }

    @Override
    public MentorshipVM getRelatedViewModel() {
        return (MentorshipVM) super.getRelatedViewModel();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onLongItemClick(View view, int position) {
        if (getRelatedViewModel().isTableSelectionStep()) {
            getRelatedViewModel().selectForm(position);
            formAdapter.notifyDataSetChanged();
        } else if (getRelatedViewModel().isMenteeSelectionStep()) {
            getRelatedViewModel().selectMentee(position);
            tutoredAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void populateMenteesList() {
        this.tutoredAdapter = new TutoredAdapter(mentorshipBinding.rcvTutored, getRelatedViewModel().getMentees(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mentorshipBinding.rcvTutored.setLayoutManager(mLayoutManager);
        mentorshipBinding.rcvTutored.setItemAnimator(new DefaultItemAnimator());
        mentorshipBinding.rcvTutored.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
        mentorshipBinding.rcvTutored.setAdapter(tutoredAdapter);
    }

    public void loadDoorAdapter() {
        doorAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getDoors());
        mentorshipBinding.spnDoor.setAdapter(doorAdapter);
    }

    public void loadSectorAdapter() {
        sectorAdapter = new ListableSpinnerAdapter(this, R.layout.simple_auto_complete_item, getRelatedViewModel().getSectors());
        mentorshipBinding.spnSector.setAdapter(sectorAdapter);
    }

    public void populateQuestionList() {
        this.questionAdapter = new QuestionAdapter(mentorshipBinding.rcvQuestions, getRelatedViewModel().getQuestionMap().get(getRelatedViewModel().getCurrQuestionCategory()), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mentorshipBinding.rcvQuestions.setLayoutManager(mLayoutManager);
        mentorshipBinding.rcvQuestions.setItemAnimator(new DefaultItemAnimator());
        mentorshipBinding.rcvQuestions.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
        mentorshipBinding.rcvQuestions.setAdapter(questionAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button click
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
