package mz.org.csaude.mentoring.viewmodel.setting;

import static mz.org.csaude.mentoring.util.Constants.METADATA_SYNC_TIME;
import static mz.org.csaude.mentoring.util.Constants.SESSION_SYNC_TIME;

import android.Manifest;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.Bindable;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.listner.rest.ServerStatusListener;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.workSchedule.executor.WorkerScheduleExecutor;

public class SettingVM extends BaseViewModel implements ServerStatusListener {

    private Setting setting;

    private SettingService settingService;
    private WorkerScheduleExecutor workerScheduleExecutor;
    public int sessionSyncTime;
    public int metadataSyncTime;

    private static final String SYNC_CHANNEL_ID = "sync_channel";
    private static final int SYNC_NOTIFICATION_ID = 1;

    public SettingVM(@NonNull Application application) {
        super(application);
        this.settingService = new SettingServiceImpl(application);
        workerScheduleExecutor = WorkerScheduleExecutor.getInstance(getApplication());
        this.sessionSyncTime = this.getApplication().getMentoringSharedPreferences().getInt(SESSION_SYNC_TIME, 2);
        this.metadataSyncTime = this.getApplication().getMentoringSharedPreferences().getInt(METADATA_SYNC_TIME, 2);
    }


    @Override
    public void preInit() {
    }

    @Bindable
    public String getDescription() {
        return this.setting.getDescription();
    }

    public void setDescription(String description) {
        this.setting.setDescription(description);
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getValue() {
        return this.setting.getValue();
    }

    public void setValue(String value) {
        this.setting.setValue(value);
        notifyPropertyChanged(BR.value);
    }

    private String designation;

    @Bindable
    public String getDesignation() {
        return this.setting.getDesignation();
    }

    public void setDesignation(String designation) {
        this.setting.setDesignation(designation);
        notifyPropertyChanged(BR.designation);
    }

    public void save() {
        try {
            this.settingService.save(this.setting)
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void syncAllNow() {
        getApplication().isServerOnline(this);
    }

    private void doSync() {
        // Create a ProgressDialog
        ProgressDialog progressDialog = new ProgressDialog(getRelatedActivity());
        progressDialog.setMessage("Sincronizando dados, aguarde por favor...");
        progressDialog.setCancelable(false); // Prevents dismissal by tapping outside the dialog
        progressDialog.show();

        // Schedule the sync work
        OneTimeWorkRequest request = workerScheduleExecutor.syncNowData();
        WorkerScheduleExecutor.getInstance(getApplication()).getWorkManager().getWorkInfoByIdLiveData(request.getId()).observe(getRelatedActivity(), info -> {
            if (info.getState() == WorkInfo.State.RUNNING) {
                // Show progress dialog when work is running
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            } else if (info.getState().isFinished()) {
                // Dismiss the progress dialog when work is finished
                progressDialog.dismiss();
            }
        });
    }

    public void saveSessionSyncTime() {
        SharedPreferences.Editor editor = this.getApplication().getMentoringSharedPreferences().edit();
        editor.putInt(SESSION_SYNC_TIME, this.sessionSyncTime);
        editor.commit();
    }
    public void saveMetadataSyncTime() {
        SharedPreferences.Editor editor = this.getApplication().getMentoringSharedPreferences().edit();
        editor.putInt(METADATA_SYNC_TIME, this.metadataSyncTime);
        editor.commit();
    }

    @Bindable
    public String getSessionSyncTime() {
        return Utilities.parseIntToString(this.getApplication().getMentoringSharedPreferences().getInt(SESSION_SYNC_TIME, 2));
    }

    public void setSessionSyncTime(String sessionSyncTime) {
        this.sessionSyncTime = Integer.parseInt(sessionSyncTime);
        this.notifyPropertyChanged(BR.sessionSyncTime);
    }

    @Bindable
    public String getMetadataSyncTime() {
        return Utilities.parseIntToString(this.getApplication().getMentoringSharedPreferences().getInt(METADATA_SYNC_TIME, 2));
    }

    public void setMetadataSyncTime(String metadataSyncTime) {
        this.metadataSyncTime = Integer.parseInt(metadataSyncTime);
        this.notifyPropertyChanged(BR.metadataSyncTime);
    }

    public void increaseMetadataSyncTime() {
        if(metadataSyncTime<24) {
            metadataSyncTime++;
            notifyPropertyChanged(BR.metadataSyncTime);
            saveMetadataSyncTime();
            getMetadataSyncTime();
        }
    }

    public void decreaseMetadataSyncTime() {
        if(metadataSyncTime>1) {
            metadataSyncTime--;
            notifyPropertyChanged(BR.metadataSyncTime);
            saveMetadataSyncTime();
            getMetadataSyncTime();
        }
    }

    public void increaseSessionSyncTime() {
        if(sessionSyncTime<24) {
            sessionSyncTime++;
            notifyPropertyChanged(BR.sessionSyncTime);
            saveSessionSyncTime();
            getSessionSyncTime();
        }
    }

    public void decreaseSessionSyncTime() {
        if(sessionSyncTime>1) {
            sessionSyncTime--;
            notifyPropertyChanged(BR.sessionSyncTime);
            saveSessionSyncTime();
            getSessionSyncTime();
        }
    }

    @Override
    public void onServerStatusChecked(boolean isOnline) {
        if (isOnline) {
            doSync();
        } else {
            Utilities.displayAlertDialog(getRelatedActivity(), getRelatedActivity().getString(R.string.server_unavailable), null).show();
        }
    }


}
