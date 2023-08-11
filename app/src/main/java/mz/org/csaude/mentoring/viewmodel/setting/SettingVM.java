package mz.org.csaude.mentoring.viewmodel.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.service.setting.SettingService;
import mz.org.csaude.mentoring.service.setting.SettingServiceImpl;

public class SettingVM extends BaseViewModel {

    private Setting setting;

    private SettingService settingService;

    public SettingVM(@NonNull Application application) {
        super(application);
        this.settingService = new SettingServiceImpl(application, getCurrentUser());
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
}
