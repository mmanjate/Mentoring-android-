package mz.org.csaude.mentoring.model.programmaticArea;


import kotlin.jvm.Transient;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class TutorProgrammaticArea extends BaseModel {

    private Tutor tutor;

    private ProgrammaticArea programmaticArea;

    @Transient
    private Boolean mapAsUser = Boolean.FALSE;

    public TutorProgrammaticArea() {
    }

    public Tutor getTutor() {
        return tutor;
    }

    public ProgrammaticArea getProgrammaticArea() {
        return programmaticArea;
    }

    public Boolean getMapAsUser() {
        return mapAsUser;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setProgrammaticArea(ProgrammaticArea programmaticArea) {
        this.programmaticArea = programmaticArea;
    }

    public void setMapAsUser(Boolean mapAsUser) {
        this.mapAsUser = mapAsUser;
    }
}
