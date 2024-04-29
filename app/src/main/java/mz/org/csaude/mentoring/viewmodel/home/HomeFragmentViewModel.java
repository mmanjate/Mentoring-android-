package mz.org.csaude.mentoring.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.tutored.TutoredActivity;

public class HomeFragmentViewModel extends BaseViewModel {

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToMentoringRounds() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Rondas de Mentorias");
        getRelatedActivity().nextActivity(RondaActivity.class, params);
    }

    public void goToBaseSessions() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Sessões Zero");
        getRelatedActivity().nextActivity(RondaActivity.class, params);
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