package mz.org.csaude.mentoring.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.view.ronda.RondaListActivity;
import mz.org.csaude.mentoring.view.tutored.TutoredActivity;

public class HomeFragmentViewModel extends BaseViewModel {

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToMentoringRounds() {
        getRelatedActivity().nextActivity(RondaListActivity.class);
    }

    public void goToBaseSessions() {

    }

    public void goToMentees() {
        getRelatedActivity().nextActivity(TutoredActivity.class);
    }

    public void goToLearningResources() {

    }
    @Override
    public void preInit() {

    }
}