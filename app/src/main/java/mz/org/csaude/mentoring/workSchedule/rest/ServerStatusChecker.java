package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.listner.rest.ServerStatusListener;

public class ServerStatusChecker extends BaseRestService {

    public ServerStatusChecker(Application application) {
        super(application);
    }

    public void isServerOnline(ServerStatusListener listener) {
        Call<Void> call = syncDataService.checkServerStatus();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onServerStatusChecked(true);
                    Log.d("ServerStatus", "Server is online");
                } else {
                    listener.onServerStatusChecked(false);
                    Log.e("ServerStatus", "Server returned an error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onServerStatusChecked(false);
                Log.e("ServerStatus", "Failed to connect to server", t);
            }
        });
    }
}

