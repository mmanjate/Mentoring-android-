package mz.org.csaude.mentoring.dto.programmaticArea;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.tutor.TutorDTO;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.tutor.Tutor;

@Data
@EqualsAndHashCode(callSuper = true)
public class TutorProgrammaticAreaDTO extends BaseEntityDTO {
    private TutorDTO tutorDTO;
    private ProgrammaticAreaDTO programmaticAreaDTO;
    public TutorProgrammaticAreaDTO() {
        super();
    }

    public TutorProgrammaticAreaDTO(TutorProgrammaticArea tutorProgrammaticArea) {
        super(tutorProgrammaticArea);
        if(tutorProgrammaticArea.getProgrammaticArea()!=null) {
            this.setProgrammaticAreaDTO(new ProgrammaticAreaDTO(tutorProgrammaticArea.getProgrammaticArea()));
        }
        if(tutorProgrammaticArea.getTutor()!=null) {
            this.setTutorDTO(new TutorDTO(tutorProgrammaticArea.getTutor()));
        }
    }

    public TutorDTO getTutorDTO() {
        return tutorDTO;
    }

    public void setTutorDTO(TutorDTO tutorDTO) {
        this.tutorDTO = tutorDTO;
    }

    public ProgrammaticAreaDTO getProgrammaticAreaDTO() {
        return programmaticAreaDTO;
    }

    public void setProgrammaticAreaDTO(ProgrammaticAreaDTO programmaticAreaDTO) {
        this.programmaticAreaDTO = programmaticAreaDTO;
    }

    public TutorProgrammaticArea getTutorProgrammaticArea() {
        TutorProgrammaticArea tutorProgrammaticArea = new TutorProgrammaticArea();
        tutorProgrammaticArea.setUuid(this.getUuid());
        tutorProgrammaticArea.setCreatedAt(this.getCreatedAt());
        tutorProgrammaticArea.setUpdatedAt(this.getUpdatedAt());
        tutorProgrammaticArea.setLifeCycleStatus(this.getLifeCycleStatus());
        if(this.getTutorDTO()!=null) tutorProgrammaticArea.setTutor(new Tutor(this.getTutorDTO()));
        if(this.getProgrammaticAreaDTO()!=null) tutorProgrammaticArea.setProgrammaticArea(new ProgrammaticArea(this.getProgrammaticAreaDTO()));
        return tutorProgrammaticArea;
    }
}
