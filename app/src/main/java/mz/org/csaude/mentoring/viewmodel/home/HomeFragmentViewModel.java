package mz.org.csaude.mentoring.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.view.ronda.RondaListActivity;

public class HomeFragmentViewModel extends BaseViewModel {

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToMentoringRounds() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(RondaListActivity.class, params);
    }

    public void goToBaseSessions() {

    }

    public void goToMentees() {

    }

    public void goToLearningResources() {

    }
    @Override
    public void preInit() {

    }
}