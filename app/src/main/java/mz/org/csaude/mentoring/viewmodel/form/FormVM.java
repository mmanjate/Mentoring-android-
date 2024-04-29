package mz.org.csaude.mentoring.viewmodel.form;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.form.FormType;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.service.form.FormService;
import mz.org.csaude.mentoring.service.form.FormServiceImpl;

public class FormVM extends BaseViewModel {

    private FormService formService;

    private Form form;

    public FormVM(@NonNull Application application) {
        super(application);
        this.formService = new FormServiceImpl(application);
    }

    @Override
    public void preInit() {

    }
    @Bindable
    public String getCode(){
        return this.form.getCode();
    }

    public void setCode(String code){
        this.form.setCode(code);
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public String getName() {
        return this.form.getName();
    }

    public void setName(String name) {
        this.form.setName(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return this.form.getDescription();
    }

    public void setDescription(String description) {
        this.form.setDescription(description);
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public ProgrammaticArea getProgrammaticArea() {
        return this.form.getProgrammaticArea();
    }

    public void setProgrammaticArea(ProgrammaticArea programmaticArea) {
        this.form.setProgrammaticArea(programmaticArea);
       notifyPropertyChanged(BR.programmaticArea);
    }

    @Bindable
    public Integer getTargetPatient() {
        return this.form.getTargetPatient();
    }

    public void setTargetPatient(Integer integer) {
        this.form.setTargetPatient(integer);
        notifyPropertyChanged(BR.targetPatient);
    }

    @Bindable
    public Integer getTargetFile() {
        return this.form.getTargetFile();
    }

    public void setTargetFile(Integer integer) {
        this.form.setTargetFile(integer);
        notifyPropertyChanged(BR.targetFile);
    }

    @Bindable
    public Partner getPartner() {
        return this.form.getPartner();
    }

    public void setPartner(Partner partner) {
        this.form.setPartner(partner);
        notifyPropertyChanged(BR.partner);
    }

    public void save() {
        try {
            this.formService.save(this.form);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
