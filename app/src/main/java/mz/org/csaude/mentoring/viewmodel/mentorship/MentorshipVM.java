package mz.org.csaude.mentoring.viewmodel.mentorship;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.mentorship.Door;
import mz.org.csaude.mentoring.model.mentorship.IterationType;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.service.form.FormService;
import mz.org.csaude.mentoring.service.form.FormServiceImpl;
import mz.org.csaude.mentoring.service.location.ProvinceService;
import mz.org.csaude.mentoring.service.location.ProvinceServiceImpl;
import mz.org.csaude.mentoring.service.mentorship.MentorshipService;
import mz.org.csaude.mentoring.service.mentorship.MentorshipServiceImpl;

public class MentorshipVM extends BaseViewModel {

    private Mentorship mentorship;
    private MentorshipService mentorshipService;

    private ProvinceService provinceService;

    private Province selectedProvince;

    private List<Form> tutorForm;

    private FormService formService;


    public MentorshipVM(@NonNull Application application) {
        super(application);
        this.mentorshipService = new MentorshipServiceImpl(application, getCurrentUser());
        provinceService = new ProvinceServiceImpl(application, getCurrentUser());
        formService = new FormServiceImpl(application, getCurrentUser());
    }

    @Override
    public void preInit() {
        this.mentorship = new Mentorship();
    }

    public void nextCategory() {}

    public void previousCategory() {}

    public void finnalizeMentorship() {}

    @Bindable
    public String getCode() {
        return this.mentorship.getCode();
    }

    public void setName(String code) {
        this.mentorship.setCode(code);
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public Date getStartDate() {
        return this.mentorship.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.mentorship.setStartDate(startDate);
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public Listble getProvince() {
        return provinceService.getAllOfTutor(mentorship.getTutor());
    }

    public void setProvince(Listble province) {
        this.selectedProvince = (Province) province;
         notifyPropertyChanged(BR.province);
    }


    @Bindable
    public Date getEndDate() {
        return this.mentorship.getEndDate();
    }

    public void setEndDate(Date endDate) {
        this.mentorship.setEndDate(endDate);
         notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public Date getPerformedDate() {
        return this.mentorship.getPerformedDate();
    }

    public void setPerformedDate(Date performedDate) {
        this.mentorship.setPerformedDate(performedDate);
        notifyPropertyChanged(BR.performedDate);
    }

    @Bindable
    public Tutor getTutor() {
        return this.mentorship.getTutor();
    }

    public void setTutor(Tutor tutor) {
        this.mentorship.setTutor(tutor);
        // notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public Tutored getTutored() {
        return this.mentorship.getTutored();
    }

    public void setTutored(Tutored tutored) {
        this.mentorship.setTutored(tutored);
         notifyPropertyChanged(BR.tutored);
    }

    @Bindable
    public Form getForm() {
        return this.mentorship.getForm();
    }

    public void setForm(Form form) {
        this.mentorship.setForm(form);
         notifyPropertyChanged(BR.form);
    }

    @Bindable
    public HealthFacility getHealthFacility() {
        return this.mentorship.getHealthFacility();
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.mentorship.setHealthFacility(healthFacility);
         notifyPropertyChanged(BR.healthFacility);
    }

    @Bindable
    public Session getSession() {
        return this.mentorship.getSession();
    }

    public void setSession(Session session) {
        this.mentorship.setSession(session);
        notifyPropertyChanged(BR.session);
    }

    @Bindable
    public Cabinet getCabinet() {
        return this.mentorship.getCabinet();
    }

    public void setCabinet(Cabinet cabinet) {
        this.mentorship.setCabinet(cabinet);
         notifyPropertyChanged(BR.cabinet);
    }

    @Bindable
    public IterationType getIterationType() {
        return this.mentorship.getIterationType();
    }

    public void setCabinet(IterationType iterationType) {
        this.mentorship.setIterationType(iterationType);
         notifyPropertyChanged(BR.iterationType);
    }

    @Bindable
    public Integer getIterationNumber() {
        return this.mentorship.getIterationNumber();
    }

    public void setIterationNumber(Integer iterationNumber) {
        this.mentorship.setIterationNumber(iterationNumber);
         notifyPropertyChanged(BR.iterationNumber);
    }

    @Bindable
    public TimeOfDay getTimeOfDay() {
        return this.mentorship.getTimeOfDay();
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.mentorship.setTimeOfDay(timeOfDay);
         notifyPropertyChanged(BR.timeOfDay);
    }

    @Bindable
    public Door getDoor() {
        return this.mentorship.getDoor();
    }

    public void setDoor(Door door) {
        this.mentorship.setDoor(door);
         notifyPropertyChanged(BR.door);
    }

    public void save() {
        try {
            this.mentorshipService.save(this.mentorship);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Form> getTutorForms() {
        return this.tutorForm;
    }

    public void loadTutorForms() {
        try {
            this.tutorForm = formService.getAllOfTutor(this.mentorship.getTutor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
