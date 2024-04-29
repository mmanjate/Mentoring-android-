package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.searchparams.AbstractSearchParams;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.base.viewModel.SearchVM;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.util.RondaType;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;

public class RondaSearchVM extends BaseViewModel {
    private final RondaService rondaService;
    private List<Listble> rondas;
    private Ronda ronda;
    public RondaSearchVM(@NonNull Application application) {
        super(application);
        this.rondas = new ArrayList<>();
        this.rondaService = getApplication().getRondaService();
    }
    @Override
    public void preInit() {
        if (getCurrentStep().isApplicationStepEdit()) {
            this.ronda = (Ronda) this.selectedListble;
        } else {
            this.ronda = new Ronda();
        }
        this.rondas = new ArrayList<>();
    }

    public void createNewRonda() {
        Map<String, Object> params = new HashMap<>();
        Intent intent = getRelatedActivity().getIntent();
        String title = (String) intent.getExtras().get("title");
        RondaType rondaType = (RondaType) intent.getExtras().get("rondaType");
        params.put("rondaType", rondaType);
        params.put("title", title);
        getRelatedActivity().nextActivityFinishingCurrent(CreateRondaActivity.class, params);
    }
    public List<Listble> getAllRondas(RondaType rondaType) {
        try {
            List<Ronda> rondaList = rondaService.getAllByRondaType(rondaType);
            for (Ronda ronda : rondaList) {
                this.rondas.add(ronda);
            }
            return this.rondas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ronda> getRondaList() {
        try {
            List<Ronda> rondaList = rondaService.getAll();
            return rondaList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Listble> getRondas() {
        return this.rondas;
    }

    public void setRondas(List<Listble> rondas) {
        this.rondas = rondas;
    }
}
