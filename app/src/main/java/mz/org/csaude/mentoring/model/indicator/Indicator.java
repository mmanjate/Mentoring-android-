package mz.org.csaude.mentoring.model.indicator;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class Indicator extends BaseModel {

    private String code;

    private LocalDateTime performedDate;

    private LocalDate referredMonth;

    private Tutor tutor;

    private Form form;

    private HealthFacility healthFacility;

    public Indicator() {
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getPerformedDate() {
        return performedDate;
    }

    public LocalDate getReferredMonth() {
        return referredMonth;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Form getForm() {
        return form;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPerformedDate(LocalDateTime performedDate) {
        this.performedDate = performedDate;
    }

    public void setReferredMonth(LocalDate referredMonth) {
        this.referredMonth = referredMonth;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }
}
