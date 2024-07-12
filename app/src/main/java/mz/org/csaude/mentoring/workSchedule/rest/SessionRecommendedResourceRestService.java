package mz.org.csaude.mentoring.workSchedule.rest;

import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Application;
import android.util.Log;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.session.SessionRecommendedResourceDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionRecommendedResourceRestService extends BaseRestService {
    public SessionRecommendedResourceRestService(Application application) {
        super(application);
    }

    public void postSessionRecommendedResource(RestResponseListener<SessionRecommendedResource> listener) {
        List<SessionRecommendedResourceDTO> resourceDTOList = null;
        try {
            List<SessionRecommendedResource> resources = getApplication().getSessionService().getPendingRecommendedResources();
            if (resources.isEmpty()) {
                return;
            }
            resourceDTOList = Utilities.parseList(resources, SessionRecommendedResourceDTO.class);
        } catch (SQLException e) {
            Log.e("SessionRecommendedResourceRestService", "Failed to get pending recommended resources", e);
        }

        Call<List<SessionRecommendedResourceDTO>> call = syncDataService.postSessionRecommendedResource(resourceDTOList);
        call.enqueue(new Callback<List<SessionRecommendedResourceDTO>>() {
            @Override
            public void onResponse(Call<List<SessionRecommendedResourceDTO>> call, Response<List<SessionRecommendedResourceDTO>> response) {
                if (response.isSuccessful()) {
                    try {
                        List<SessionRecommendedResource> resources = Utilities.parseList(response.body(), SessionRecommendedResource.class);
                        for (SessionRecommendedResource resource : resources) {
                            resource.setSyncStatus(SyncSatus.SENT);
                            getApplication().getSessionService().updateRecommendedResources(resource);
                        }

                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, Collections.emptyList());
                    } catch (SQLException e) {
                        Log.e("SessionRecommendedResourceRestService", "Failed to update recommended resources", e);
                    }
                } else {
                    listener.doOnRestErrorResponse("Failed to save session recommended resource: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SessionRecommendedResourceDTO>> call, Throwable t) {
                listener.doOnRestErrorResponse("Network error: " + t.getMessage());
            }
        });
    }
}
