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
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.view.mentorship.MentorshipActivity;
import mz.org.csaude.mentoring.view.mentorship.ZeroMentorshipListActivity;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;

public class RondaSearchVM extends SearchVM<Ronda> {
    private RondaType rondaType;

    private String title;

    public RondaSearchVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void doOnNoRecordFound() {

    }

    @Override
    public void preInit() {
    }

    public void createNewRonda() {
        Map<String, Object> params = new HashMap<>();
        params.put("rondaType", rondaType);
        params.put("title", title);
        getRelatedActivity().nextActivity(CreateRondaActivity.class, params);
    }

    public RondaType getRondaType() {
        return rondaType;
    }

    public void setRondaType(RondaType rondaType) {
        this.rondaType = rondaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Ronda> doSearch(long offset, long limit) throws SQLException {
        return getApplication().getRondaService().getAllByRondaType(this.rondaType);
    }

    @Override
    public void displaySearchResults() {
        getRelatedActivity().populateRecyclerView();
    }

    @Override
    public RondaActivity getRelatedActivity() {
        return (RondaActivity) super.getRelatedActivity();
    }

    @Override
    public AbstractSearchParams<Ronda> initSearchParams() {
        return null;
    }

    public void goToMentoriships(Ronda ronda) {

        if (ronda.isRondaZero()) {
            Map<String, Object> params = new HashMap<>();
            params.put("ronda", ronda);
            getApplication().getApplicationStep().changeToList();

            getRelatedActivity().nextActivity(ZeroMentorshipListActivity.class, params);
        }else {
            Map<String, Object> params = new HashMap<>();
            params.put("ronda", ronda);
            params.put("title", title);
            getRelatedActivity().nextActivity(SessionActivity.class, params);
        }
    }
}
