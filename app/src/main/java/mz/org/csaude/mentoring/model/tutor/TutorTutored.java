package mz.org.csaude.mentoring.model.tutor;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.tutor.TutorTutoredDaoImpl;
import mz.org.csaude.mentoring.model.tutored.Tutored;


@DatabaseTable(tableName = TutorTutored.TABLE_NAME, daoClass = TutorTutoredDaoImpl.class)
public class TutorTutored extends BaseModel {

    public static final String TABLE_NAME = "tutor_tutored";

    public static final String COLUMN_TUTOR = "tutor_id";

    public static final String COLUMN_TUTORED = "tutored_id";

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_TUTORED, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutored tutored;

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Tutored getTutored() {
        return tutored;
    }

    public void setTutored(Tutored tutored) {
        this.tutored = tutored;
    }
}
