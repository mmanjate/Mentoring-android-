package mz.org.csaude.mentoring.model.programmaticArea;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import kotlin.jvm.Transient;
import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.programmaticArea.TutorProgrammaticAreaDAOImpl;
import mz.org.csaude.mentoring.model.tutor.Tutor;

@Data
@DatabaseTable(tableName = TutorProgrammaticArea.TABLE_NAME, daoClass = TutorProgrammaticAreaDAOImpl.class)
public class TutorProgrammaticArea extends BaseModel {

    public static final String TABLE_NAME = "tutor_programmatic_area";

    public static final String COLUMN_TUTOR = "tutor_id";

    public static final String COLUMN_PROGRAMMATIC_AREA = "programmatic_area_id";

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_PROGRAMMATIC_AREA, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private ProgrammaticArea programmaticArea;

    @Transient
    private Boolean mapAsUser = Boolean.FALSE;

    public TutorProgrammaticArea() {
    }

    public TutorProgrammaticArea(Tutor tutor, ProgrammaticArea programmaticArea, Boolean mapAsUser) {
        this.tutor = tutor;
        this.programmaticArea = programmaticArea;
        this.mapAsUser = mapAsUser;
    }
}
