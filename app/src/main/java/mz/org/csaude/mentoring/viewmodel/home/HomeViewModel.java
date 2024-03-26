package mz.org.csaude.mentoring.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.view.home.MainActivity;
import mz.org.csaude.mentoring.view.ronda.RondaListActivity;

public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToMentoringRounds() {

        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(RondaListActivity.class, params);
    }

    @Override
    public void preInit() {

    }
}