package mz.org.csaude.mentoring.viewmodel.form;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.form.FormTarget;
import mz.org.csaude.mentoring.service.form.FormTargetService;
import mz.org.csaude.mentoring.service.form.FormTargetServiceImpl;

public class FormTargetVM extends BaseViewModel {

    private FormTargetService formTargetService;

    private FormTarget formTarget;

    public FormTargetVM(@NonNull Application application) {
        super(application);
        this.formTargetService = new FormTargetServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Form getForm() {
        return this.formTarget.getForm();
    }

    public void setName(Form form) {
        this.formTarget.setForm(form);
        notifyPropertyChanged(BR.form);
    }

    @Bindable
    public Career getCareer() {
        return this.formTarget.getCareer();
    }

    public void setName(Career career) {
        this.formTarget.setCareer(career);
        notifyPropertyChanged(BR.career);
    }

    @Bindable
    public Integer getInteger() {
        return this.formTarget.getTarget();
    }

    public void setInteger(Integer integer) {
        this.formTarget.setTarget(integer);
        notifyPropertyChanged(BR.integer);
    }

    public void save() {
        try {
            this.formTargetService.save(this.formTarget);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
