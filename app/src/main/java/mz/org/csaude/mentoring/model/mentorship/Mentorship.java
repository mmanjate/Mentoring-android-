package mz.org.csaude.mentoring.model.mentorship;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class Mentorship extends BaseModel {

    private String code;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDate performedDate;

    private Tutor tutor;

    private Tutored tutored;

    private Form form;

    private HealthFacility healthFacility;

    private Session session;

    private Cabinet cabinet;

    private IterationType iterationType;

    private Integer iterationNumber;

    private TimeOfDay timeOfDay;

    private Door door;

    public Mentorship() {
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDate getPerformedDate() {
        return performedDate;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Tutored getTutored() {
        return tutored;
    }

    public Form getForm() {
        return form;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public Session getSession() {
        return session;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public IterationType getIterationType() {
        return iterationType;
    }

    public Integer getIterationNumber() {
        return iterationNumber;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public Door getDoor() {
        return door;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPerformedDate(LocalDate performedDate) {
        this.performedDate = performedDate;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setTutored(Tutored tutored) {
        this.tutored = tutored;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public void setIterationType(IterationType iterationType) {
        this.iterationType = iterationType;
    }

    public void setIterationNumber(Integer iterationNumber) {
        this.iterationNumber = iterationNumber;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
