package mz.org.csaude.mentoring.viewmodel.answer;

import android.app.Application;

import androidx.annotation.NonNull;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.service.answer.AnswerService;

public class AnswerVM extends BaseViewModel {

    private Answer answer;

    private AnswerService answerService;

    public AnswerVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void preInit() {

    }
}
