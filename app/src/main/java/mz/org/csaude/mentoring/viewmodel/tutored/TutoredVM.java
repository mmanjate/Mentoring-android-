package mz.org.csaude.mentoring.viewmodel.tutored;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.career.CareerType;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.career.CareerService;
import mz.org.csaude.mentoring.service.career.CareerServiceImpl;
import mz.org.csaude.mentoring.service.career.CareerTypeService;
import mz.org.csaude.mentoring.service.career.CareerTypeServiceImpl;
import mz.org.csaude.mentoring.service.session.SessionService;
import mz.org.csaude.mentoring.service.tutored.TutoredService;
import mz.org.csaude.mentoring.service.tutored.TutoredServiceImpl;

public class TutoredVM extends BaseViewModel {

    private TutoredService tutoredService;
    private CareerTypeService careerTypeService;
    private CareerService careerService;
    private SessionService sessionService;
    private Tutored tutored;
    private CareerType selectedCareerType;
    private Career selectedCareer;

    public TutoredVM(@NonNull Application application) {
        super(application);

        this.tutoredService = new TutoredServiceImpl(application, getCurrentUser());
        this.careerTypeService = new CareerTypeServiceImpl(application, getCurrentUser());
        this.careerService = new CareerServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getName() {
        return this.tutored.getName();
    }

    public void setName(String name) {
        this.tutored.setName(name);
        notifyPropertyChanged(BR.name);
    }
    public List<CareerType> getCareerTypes() throws SQLException {
        return this.careerTypeService.getAll();
    }
    @Bindable
    public Listble getCareerType() {
        return this.getCareerType();
    }

    public void setCareerType(Listble careerType) {
        this.selectedCareerType = (CareerType) careerType;
        try {
            this.getCareers().clear();
            this.getCareers().addAll(this.careerService.getCareersByCareerType((CareerType) careerType));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        notifyPropertyChanged(BR.careerType);
    }
    public List<Career> getCareers() throws SQLException {
        return this.careerService.getAll();
    }
    @Bindable
    public Listble getCareer() {
        return this.getCareer();
    }

    public void setCareer(Listble career) {
        this.selectedCareer = (Career) career;
        notifyPropertyChanged(BR.career);
    }

    public List<Tutored> getAllTutoreds() throws SQLException {
        return tutoredService.getAll();
    }

    public Tutored save() {
        Tutored tutored = null;
        try {
            tutored = this.tutoredService.save(this.tutored);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tutored;
    }

    public Tutored getTutored() {
        return this.tutored;
    }

    public void setTutored(Tutored tutored) {
        this.tutored = tutored;
    }

    public void deleteTutored(Tutored tutored) throws SQLException {
        this.tutoredService.delete(tutored);
    }

    public String tutoredHasSessions() {
        /*boolean hasSessions = false;
        try {
            hasSessions = this.episodeService.patientHasEndingEpisode(getDispense().getPrescription().getPatient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hasEndingEpisode) {
            return getRelatedActivity().getString(R.string.dispense_has_final_episode_cant_be_edit);
        }*/
        return "";
    }

}
