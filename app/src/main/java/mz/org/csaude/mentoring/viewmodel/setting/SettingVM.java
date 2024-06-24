package mz.org.csaude.mentoring.viewmodel.setting;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;
import mz.org.csaude.mentoring.workSchedule.executor.WorkerScheduleExecutor;

public class SettingVM extends BaseViewModel {

    private Setting setting;

    private SettingService settingService;
    private WorkerScheduleExecutor workerScheduleExecutor;

    private SharedPreferences sharedPreferences;

    public int sessionSyncTime;

    public int metadataSyncTime;

    public SettingVM(@NonNull Application application) {
        super(application);
        this.settingService = new SettingServiceImpl(application);
        workerScheduleExecutor = WorkerScheduleExecutor.getInstance(getApplication());
        this.sharedPreferences = ((MentoringApplication) application).getMentoringSharedPreferences();
    }

    @Override
    public void preInit() {
        this.sessionSyncTime = 1;
        this.metadataSyncTime = 1;
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
        workerScheduleExecutor.syncNowData();
    }

    public void saveSessionSyncTime() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt("session_sync_time_key", this.sessionSyncTime);
        editor.commit();
    }
    public void saveMetadataSyncTime() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt("metadata_sync_time_key", this.metadataSyncTime);
        editor.commit();
    }
}
