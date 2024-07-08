package mz.org.csaude.mentoring.viewmodel.ronda;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.base.searchparams.AbstractSearchParams;
import mz.org.csaude.mentoring.base.viewModel.SearchVM;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.ronda.RondaSummary;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionSummary;
import mz.org.csaude.mentoring.util.PDFGenerator;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.mentorship.MentorshipActivity;
import mz.org.csaude.mentoring.view.mentorship.ZeroMentorshipListActivity;
import mz.org.csaude.mentoring.view.ronda.CreateRondaActivity;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;
import mz.org.csaude.mentoring.view.session.SessionListActivity;

public class RondaSearchVM extends SearchVM<Ronda> implements IDialogListener {
    private RondaType rondaType;

    private String title;

    private Ronda selectedRonda;

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
        getApplication().getApplicationStep().changetocreate();
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
        getApplication().getApplicationStep().changeToList();
        Map<String, Object> params = new HashMap<>();
        params.put("ronda", ronda);

        if (ronda.isRondaZero()) {
            Log.d("goToMentoriships", "Navigating to ZeroMentorshipListActivity with params: " + params);
            getRelatedActivity().nextActivity(ZeroMentorshipListActivity.class, params);
        } else {
            Log.d("goToMentoriships", "Navigating to SessionListActivity with params: " + params);
            getRelatedActivity().nextActivity(SessionListActivity.class, params);
        }
    }

    public void printRondaSummary(Ronda ronda) {
        try {
            List<RondaSummary> rondaSummaryList = new ArrayList<>();

            ronda = getApplication().getRondaService().getFullyLoadedRonda(ronda);

            for (RondaMentee mentee : ronda.getRondaMentees()){
                RondaSummary rondaSummary = new RondaSummary();
                rondaSummary.setRonda(ronda);
                rondaSummary.setMentor(ronda.getActiveMentor().getEmployee().getFullName());
                rondaSummary.setMentee(mentee.getTutored().getEmployee().getFullName());
                rondaSummary.setNuit(mentee.getTutored().getEmployee().getNuit());
                List<Session> sessions = new ArrayList<>();
                for (Session session : ronda.getSessions()){
                    if (session.getTutored().equals(mentee.getTutored())){
                        sessions.add(session);
                    }
                }
                sessions.sort(Comparator.comparing(Session::getStartDate));
                rondaSummary.setSummaryDetails(new HashMap<>());
                int i = 1;
                for (Session session : sessions){
                    rondaSummary.getSummaryDetails().put(i, getApplication().getSessionService().generateSessionSummary(session));

                    i++;
                }
                rondaSummary.setSession1(determineSessionScore(rondaSummary.getSummaryDetails().get(1)));
                rondaSummary.setSession2(determineSessionScore(rondaSummary.getSummaryDetails().get(2)));
                rondaSummary.setSession3(determineSessionScore(rondaSummary.getSummaryDetails().get(3)));
                rondaSummary.setSession4(determineSessionScore(rondaSummary.getSummaryDetails().get(4)));
                rondaSummaryList.add(rondaSummary);
            }
            boolean print = PDFGenerator.createRondaSummary(getRelatedActivity(), rondaSummaryList);
            if (print) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Resumo impresso com sucesso, encontre o documento no diretório de downloads.").show();
            } else {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não foi possível imprimir o documento.").show();
            }

        } catch (SQLException e) {
            Log.e("printRondaSummary", "Exception: " + e.getMessage());
        }
    }

    private double determineSessionScore(List<SessionSummary> sessionSummaries) {
        int yesCount = 0;
        int noCount = 0;
        for (SessionSummary sessionSummary : sessionSummaries){
            yesCount = yesCount + sessionSummary.getSimCount();
            noCount = noCount + sessionSummary.getNaoCount();
        }
        return (double) yesCount / (yesCount + noCount) *100;
    }

    public void edit(Ronda ronda) {
        try {
            ronda.setSessions(getApplication().getSessionService().getAllOfRonda(ronda));
            if (Utilities.listHasElements(ronda.getSessions())) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma ronda com sessões de mentoria registadas.").show();
                return;
            }
        } catch (SQLException e) {
            Log.e("Ronda Search VM", "Exception: " + e.getMessage());
        }

        Map<String, Object> params = new HashMap<>();
        params.put("ronda", ronda);
        getApplication().getApplicationStep().changeToEdit();
        getRelatedActivity().nextActivity(CreateRondaActivity.class, params);

    }

    public void delete(Ronda ronda) {
        this.selectedRonda = ronda;
        try {
            ronda.setSessions(getApplication().getSessionService().getAllOfRonda(ronda));
            if (Utilities.listHasElements(ronda.getSessions())) {
                Utilities.displayAlertDialog(getRelatedActivity(), "Não é possível editar uma ronda com sessões de mentoria registadas.").show();
                return;
            }
        } catch (SQLException e) {
            Log.e("Ronda Search VM", "Exception: " + e.getMessage());
        }

        Utilities.displayConfirmationDialog(getRelatedActivity(), "Deseja realmente excluir a ronda?", "Sim", "Não", this).show();
    }

    @Override
    public void doOnConfirmed() {
        try {
            getApplication().getRondaService().delete(selectedRonda);
            initSearch();
        } catch (SQLException e) {
            Log.e("Ronda Search VM", "Exception: " + e.getMessage());
        }
    }

    @Override
    public void doOnDeny() {

    }
}
