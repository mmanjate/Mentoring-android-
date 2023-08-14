package mz.org.csaude.mentoring.model.tutor;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.location.HealthFacility;

public class TutorLocation extends BaseModel {

    private Tutor tutor;

    private HealthFacility healthFacility;

    public TutorLocation() {
    }

    public Tutor getTutor() {
        return tutor;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }
}
