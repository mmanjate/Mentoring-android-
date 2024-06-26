package mz.org.csaude.mentoring.viewmodel.session;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.searchparams.AbstractSearchParams;
import mz.org.csaude.mentoring.base.viewModel.SearchVM;
import mz.org.csaude.mentoring.listner.dialog.IDialogListener;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.resourceea.Resource;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.session.SessionSummaryActivity;

public class SessionResourcesVM extends SearchVM<Resource> implements IDialogListener {

    private boolean recommendResources;

    private String searchText;
    private List<Node> nodeList = new ArrayList<>();

    private List<SessionRecommendedResource> recommendedResources;
    private Session session;
    public SessionResourcesVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public boolean isRecommendResources() {
        return recommendResources;
    }


    public void setRecommendResources(boolean recommendResources) {
        this.recommendResources = recommendResources;
        notifyPropertyChanged(BR.recommendResources);
    }

    public void changeRecommendResourcesStatus(){
        setRecommendResources(!isRecommendResources());
    }
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Resource> doSearch(long offset, long limit) throws SQLException {
        return getApplication().getResourceService().getAll();
    }

    @Bindable
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        notifyPropertyChanged(BR.searchText);
    }

    @Override
    public void displaySearchResults() {
        try {
            JSONArray jsonArray = new JSONArray(getSearchResults().get(0).getResource());

            List<JSONObject> filteredChildren = getChildrenWithNameAndDescription(jsonArray, null, null, null);
            this.nodeList.clear();

            List<Node> nodes = convertToNodeList(filteredChildren);
            if (Utilities.listHasElements(nodes)) {
                if (Utilities.stringHasValue(getSearchText())) {
                    for (Node node : nodes) {
                        if (node.getName().contains(getSearchText())) {
                            this.nodeList.add(node);
                        }
                    }
                } else this.nodeList.addAll(nodes);
            }
            if (!Utilities.listHasElements(nodeList)) {
                doOnNoRecordFound();
                return;
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        getRelatedActivity().displaySearchResults();
    }

    @Override
    public AbstractSearchParams<Resource> initSearchParams() {
        return null;
    }

    @Override
    protected void doOnNoRecordFound() {
        Utilities.displayAlertDialog(getRelatedActivity(), "Não foram encontrados resultados").show();
    }

    public void closeSession() {
        Utilities.displayConfirmationDialog(getRelatedActivity(), "Confirma Terminar a Sessão de Mentoria?", "Sim", "Não", this).show();
    }

    @Override
    public void doOnConfirmed() {
        // close session
        goToSessionSummary();
    }

    private void goToSessionSummary() {
        try {
            getApplication().getSessionService().update(session);
            if (isRecommendResources()) {
                getApplication().getSessionService().saveRecommendedResources(session, recommendedResources);
            }
        } catch (SQLException e) {
            Log.e(TAG, "goToSessionSummary: ", e.getCause());
        }
        Map<String, Object> params = new HashMap<>();
        params.put("session", session);
        getRelatedActivity().nextActivity(SessionSummaryActivity.class, params);
    }

    @Override
    public void doOnDeny() {

    }

    public List<Node> getNodeList() {
        return nodeList;
    }


    public static List<JSONObject> getChildrenWithNameAndDescription(JSONArray jsonArray, String parentLabel, String grandparentLabel, String greatGrandparentLabel) throws JSONException {
        List<JSONObject> result = new ArrayList<>();
        traverseJSON(jsonArray, result, parentLabel, grandparentLabel, greatGrandparentLabel);
        return result;
    }

    private static void traverseJSON(JSONArray jsonArray, List<JSONObject> result, String parentLabel, String grandparentLabel, String greatGrandparentLabel) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String currentLabel = jsonObject.optString("label");
            if (jsonObject.has("children")) {
                traverseJSON(jsonObject.getJSONArray("children"), result, currentLabel, parentLabel, grandparentLabel);
            } else if (jsonObject.has("name") && jsonObject.has("description")) {
                jsonObject.put("parentLabel", parentLabel);
                jsonObject.put("grandparentLabel", grandparentLabel);
                jsonObject.put("greatGrandparentLabel", greatGrandparentLabel);
                result.add(jsonObject);
            }
        }
    }

    public static List<Node> convertToNodeList(List<JSONObject> jsonObjects) throws JSONException {
        List<Node> nodeList = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects) {
            Node node = new Node();
            node.setName(jsonObject.getString("name"));
            node.setDescription(jsonObject.getString("description"));
            node.setLabel(jsonObject.optString("label"));
            node.setClickable(jsonObject.optInt("clickable"));
            node.setIcon(jsonObject.optString("icon"));
            node.setProgram(jsonObject.optString("greatGrandparentLabel"));
            node.setCategory(jsonObject.optString("grandparentLabel"));
            node.setSubCategory(jsonObject.optString("parentLabel"));
            node.setResource(jsonObject.optString("resource"));
            node.setType(jsonObject.optString("type"));
            nodeList.add(node);
        }
        return nodeList;
    }

    public void selectResource(int position) {
        Node node = this.nodeList.get(position);
        node.setItemSelected(!node.isSelected());

        if (node.isSelected()) {
            if (recommendedResources == null) {
                recommendedResources = new ArrayList<>();
            }
            recommendedResources.add(new SessionRecommendedResource(session, node));
        } else {
            recommendedResources.remove(new SessionRecommendedResource(session, node));
        }
    }
}
