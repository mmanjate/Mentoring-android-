package mz.org.csaude.mentoring.view.answer;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.AnswerActivityBinding;
import mz.org.csaude.mentoring.viewmodel.answer.AnswerVM;

public class AnswerActivity extends BaseActivity {

    AnswerActivityBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.answer_activity);

        activityBinding.setViewModel(getRelatedViewModel());
    }
    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(AnswerVM.class);
    }

    @Override
    public AnswerVM getRelatedViewModel() {
        return (AnswerVM) super.getRelatedViewModel();
    }


}
