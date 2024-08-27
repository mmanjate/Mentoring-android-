package mz.org.csaude.mentoring.model.programmaticArea;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.programmaticArea.TutorProgrammaticAreaDAOImpl;
import mz.org.csaude.mentoring.dto.programmaticArea.TutorProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.tutor.Tutor;


@DatabaseTable(tableName = TutorProgrammaticArea.TABLE_NAME, daoClass = TutorProgrammaticAreaDAOImpl.class)

public class TutorProgrammaticArea extends BaseModel {

    public static final String TABLE_NAME = "tutor_programmatic_area";

    public static final String COLUMN_TUTOR = "tutor_id";

    public static final String COLUMN_PROGRAMMATIC_AREA = "programmatic_area_id";

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_PROGRAMMATIC_AREA, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ProgrammaticArea programmaticArea;


    public TutorProgrammaticArea() {
    }

    public TutorProgrammaticArea(Tutor tutor, ProgrammaticArea programmaticArea) {
        this.tutor = tutor;
        this.programmaticArea = programmaticArea;
    }

    public TutorProgrammaticArea(TutorProgrammaticAreaDTO tutorProgrammaticAreaDTO) {
        super(tutorProgrammaticAreaDTO);
        this.setTutor(new Tutor(tutorProgrammaticAreaDTO.getTutorDTO()));
        this.setProgrammaticArea(new ProgrammaticArea(tutorProgrammaticAreaDTO.getProgrammaticAreaDTO()));
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public ProgrammaticArea getProgrammaticArea() {
        return programmaticArea;
    }

    public void setProgrammaticArea(ProgrammaticArea programmaticArea) {
        this.programmaticArea = programmaticArea;
    }
}
