package mz.org.csaude.mentoring.service.resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.resourceea.Resource;

public interface ResourceService extends BaseService<Resource> {

    void savedOrUpdateResource(Resource resource) throws SQLException;
    void savedOrUpdateResources(List<Resource> resources) throws SQLException;

    List<JSONObject> getChildrenWithNameAndDescription(JSONArray jsonArray, String parentLabel, String grandparentLabel, String greatGrandparentLabel) throws JSONException;

    List<Node> convertToNodeList(List<JSONObject> jsonObjects) throws JSONException;
}
