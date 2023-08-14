package mz.org.csaude.mentoring.model.mentorship;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.mentorship.MentorshipDAOImpl;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;

@Data
@DatabaseTable(tableName = Mentorship.TABLE_NAME, daoClass = MentorshipDAOImpl.class)
public class Mentorship extends BaseModel {

    public static final String TABLE_NAME = "mentorship";

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_START_DATE = "start_date";

    public static final String COLUMN_END_DATE = "end_date";

    public static final String COLUMN_PERFORMED_DATE = "performed_date";

    public static final String COLUMN_TUTOR = "tutor_id";

    public static final String COLUMN_TUTORED = "tutored_id";

    public static final String COLUMN_FORM = "form_id";

    public static final String COLUMN_HEALTH_FACILITY = "health_facility_id";

    public static final String COLUMN_SESSION = "session_id";

    public static final String COLUMN_CABINET = "cabinet_id";

    public static final String COLUMN_ITERATION_TYPE = "iteration_type_id";

    public static final String COLUMN_ITERATION_NUMBER = "iteration_number";

    public static final String COLUMN_TIME_OF_DAY = "time_of_day_id";

    public static final String COLUMN_DOOR = "door_id";

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    @DatabaseField(columnName = COLUMN_START_DATE, canBeNull = false)
    private LocalDateTime startDate;

    @DatabaseField(columnName = COLUMN_END_DATE, canBeNull = false)
    private LocalDateTime endDate;

    @DatabaseField(columnName = COLUMN_PERFORMED_DATE, canBeNull = false)
    private LocalDate performedDate;

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_TUTORED, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutored tutored;

    @DatabaseField(columnName = COLUMN_FORM, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Form form;

    @DatabaseField(columnName = COLUMN_HEALTH_FACILITY, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private HealthFacility healthFacility;

    @DatabaseField(columnName = COLUMN_SESSION, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Session session;

    @DatabaseField(columnName = COLUMN_CABINET, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Cabinet cabinet;

    @DatabaseField(columnName = COLUMN_ITERATION_TYPE, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private IterationType iterationType;

    @DatabaseField(columnName = COLUMN_ITERATION_NUMBER, canBeNull = false)
    private Integer iterationNumber;

    @DatabaseField(columnName = COLUMN_TIME_OF_DAY, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private TimeOfDay timeOfDay;

    @DatabaseField(columnName = COLUMN_DOOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Door door;

    public Mentorship() {
    }

    public Mentorship(String code, LocalDateTime startDate, LocalDateTime endDate, LocalDate performedDate, Tutor tutor, Tutored tutored, Form form, HealthFacility healthFacility, Session session, Cabinet cabinet, IterationType iterationType, Integer iterationNumber, TimeOfDay timeOfDay, Door door) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.performedDate = performedDate;
        this.tutor = tutor;
        this.tutored = tutored;
        this.form = form;
        this.healthFacility = healthFacility;
        this.session = session;
        this.cabinet = cabinet;
        this.iterationType = iterationType;
        this.iterationNumber = iterationNumber;
        this.timeOfDay = timeOfDay;
        this.door = door;
    }
}
