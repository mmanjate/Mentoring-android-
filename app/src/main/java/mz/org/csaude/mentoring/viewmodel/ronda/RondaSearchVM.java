package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.base.searchparams.AbstractSearchParams;
import mz.org.csaude.mentoring.base.viewModel.SearchVM;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.service.ronda.RondaService;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.ronda.RondaListActivity;

public class RondaSearchVM extends SearchVM<Ronda> {

    private final RondaService rondaService;
    public RondaSearchVM(@NonNull Application application) {
        super(application);
        this.rondaService = getApplication().getRondaService();
    }

    @Override
    public void preInit() {

    }

    @Override
    public List<Ronda> doSearch(long offset, long limit) throws SQLException {
        return rondaService.doSearch(offset, limit);
    }

    @Override
    public void displaySearchResults() {
        //getRelatedActivity().
    }

    @Override
    public AbstractSearchParams<Ronda> initSearchParams() {
        return null;
    }

    @Override
    protected void doOnNoRecordFound() {

    }

    public void createNewRonda() {
        Map<String, Object> params = new HashMap<>();
        getRelatedActivity().nextActivityFinishingCurrent(RondaActivity.class, params);
    }
}
