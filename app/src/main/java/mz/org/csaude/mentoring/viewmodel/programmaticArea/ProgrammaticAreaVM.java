package mz.org.csaude.mentoring.viewmodel.programmaticArea;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.service.ProgrammaticArea.ProgrammaticAreaService;
import mz.org.csaude.mentoring.service.ProgrammaticArea.ProgrammaticAreaServiceImpl;

public class ProgrammaticAreaVM extends BaseViewModel {


    private ProgrammaticArea programmaticArea;

    private ProgrammaticAreaService programmaticAreaService;

    public ProgrammaticAreaVM(@NonNull Application application) {
        super(application);

        this.programmaticAreaService = new ProgrammaticAreaServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getCode() {
        return this.programmaticArea.getCode();
    }

    public void setCode(String code) {
        this.programmaticArea.setCode(code);
        notifyPropertyChanged(BR.code);
    }

    private String name;

    @Bindable
    public String getName() {
        return this.programmaticArea.getName();
    }

    public void setName(String name) {
        this.programmaticArea.setName(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return this.programmaticArea.getDescription();
    }

    public void setDescription(String description) {
        this.programmaticArea.setDescription(description);
        notifyPropertyChanged(BR.description);
    }

    public void save() {
        try {
            this.programmaticAreaService.save(this.programmaticArea);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
