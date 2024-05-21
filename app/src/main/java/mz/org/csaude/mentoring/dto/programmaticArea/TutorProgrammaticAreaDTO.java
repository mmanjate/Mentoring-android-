package mz.org.csaude.mentoring.dto.programmaticArea;

import lombok.Data;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.tutor.TutorDTO;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
@Data
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
        tutorProgrammaticArea.setId(this.getId());
        tutorProgrammaticArea.setUuid(this.getUuid());
        tutorProgrammaticArea.setCreatedAt(this.getCreatedAt());
        tutorProgrammaticArea.setUpdatedAt(this.getUpdatedAt());
        if(this.getProgrammaticAreaDTO()!=null) {
            tutorProgrammaticArea.setProgrammaticArea(this.programmaticAreaDTO.getProgrammaticArea());
        }
        if(this.getTutorDTO()!=null) {
            tutorProgrammaticArea.setTutor(this.tutorDTO.getTutor());
        }
        return tutorProgrammaticArea;
    }
}
