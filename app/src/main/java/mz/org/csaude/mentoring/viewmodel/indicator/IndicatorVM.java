package mz.org.csaude.mentoring.viewmodel.indicator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.Date;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.indicator.Indicator;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.service.indicator.IndicatorService;
import mz.org.csaude.mentoring.service.indicator.IndicatorServiceImpl;

public class IndicatorVM extends BaseViewModel {

    private Indicator indicator;
    private IndicatorService indicatorService;

    public IndicatorVM(@NonNull Application application) {
        super(application);
        this.indicatorService = new IndicatorServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getCode() {
        return this.indicator.getCode();
    }

    public void setCode(String code) {
        this.indicator.setCode(code);
        notifyPropertyChanged(BR.code);
    }
    private Date performedDate;

    @Bindable
    public Date getPerformedDate() {
        return this.indicator.getPerformedDate();
    }

    public void setPerformedDate(Date performedDate) {
        this.indicator.setPerformedDate(performedDate);
        notifyPropertyChanged(BR.performedDate);
    }

    @Bindable
    public Date getReferredMonth() {
        return this.indicator.getReferredMonth();
    }

    public void setReferredMonth(Date referredMonth) {
        this.indicator.setReferredMonth(referredMonth);
        notifyPropertyChanged(BR.referredMonth);

    }

    @Bindable
    public Tutor getTutor() {
        return this.indicator.getTutor();
    }

    public void setTutor(Tutor tutor) {
        this.indicator.setTutor(tutor);
        notifyPropertyChanged(BR.tutor);
    }

    @Bindable
    public Form getForm() {
        return this.indicator.getForm();
    }

    public void setTutor(Form form) {
        this.indicator.setForm(form);
        notifyPropertyChanged(BR.form);
    }

    @Bindable
    public HealthFacility getHealthFacility() {
        return this.indicator.getHealthFacility();
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.indicator.setHealthFacility(healthFacility);
        notifyPropertyChanged(BR.healthFacility);
    }

    public void save() {
        try {
            this.indicatorService.save(this.indicator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
