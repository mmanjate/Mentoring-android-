package mz.org.csaude.mentoring.viewmodel.setting;

import static mz.org.csaude.mentoring.util.Constants.METADATA_SYNC_TIME;
import static mz.org.csaude.mentoring.util.Constants.SESSION_SYNC_TIME;

import android.Manifest;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.workSchedule.executor.WorkerScheduleExecutor;

public class SettingVM extends BaseViewModel {

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
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sync Notifications";
            String description = "Notifications for data sync status";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(SYNC_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplication().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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

    private NotificationCompat.Builder buildProgressNotification(Context context, String title, String content, int progress) {
        return new NotificationCompat.Builder(context, "sync_channel")
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.baseline_cloud_sync_24)  // Replace with your sync icon drawable
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(100, progress, false);
    }
    public void syncAllNow() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        int notificationId = 1;

        // Start the notification with progress
        NotificationCompat.Builder builder = buildProgressNotification(getApplication(), "Sync In Progress", "Syncing data...", 0);
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());

        OneTimeWorkRequest request = workerScheduleExecutor.syncNowData();
        WorkerScheduleExecutor.getInstance(getApplication()).getWorkManager().getWorkInfoByIdLiveData(request.getId()).observe(getRelatedActivity(), info -> {
            if (info.getState() == WorkInfo.State.RUNNING) {
                // Update progress on the notification
                int progress = info.getProgress().getInt("PROGRESS", 0);
                builder.setProgress(100, progress, false);
                if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                notificationManager.notify(notificationId, builder.build());
            } else if (info.getState().isFinished()) {
                // Remove progress, mark as complete
                builder.setContentTitle("Sync Complete")
                        .setContentText("Data sync complete.")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                notificationManager.notify(notificationId, builder.build());
            }
        });
    }

    private void showProgressNotification(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication(), SYNC_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setOngoing(true)
                .setProgress(100, 0, true);

        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        NotificationManagerCompat.from(getApplication()).notify(SYNC_NOTIFICATION_ID, builder.build());
    }

    private void showNotification(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication(), SYNC_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_sys_download_done)
                .setProgress(0, 0, false)
                .setOngoing(false);

        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        NotificationManagerCompat.from(getApplication()).notify(SYNC_NOTIFICATION_ID, builder.build());
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
}
