package mz.org.csaude.mentoring.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.util.RondaTypeEnum;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.tutored.TutoredActivity;

public class HomeFragmentViewModel extends BaseViewModel {

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToMentoringRounds() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("title", "Rondas de Mentorias");
            params.put("rondaType", getApplication().getRondaTypeService().getRondaTypeByCode(RondaTypeEnum.MENTORIA_INTERNA.toString()));
            getRelatedActivity().nextActivityFinishingCurrent(RondaActivity.class, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToBaseSessions() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("title", "Sessões Zero");
            params.put("rondaType", getApplication().getRondaTypeService().getRondaTypeByCode(RondaTypeEnum.SESSAO_ZERO.toString()));
            getRelatedActivity().nextActivityFinishingCurrent(RondaActivity.class, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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