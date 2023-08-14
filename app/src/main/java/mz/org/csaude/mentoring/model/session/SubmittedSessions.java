package mz.org.csaude.mentoring.model.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

import java.util.Calendar;

@Data
@DatabaseTable(tableName = SubmittedSessions.TABLE_NAME)
public class SubmittedSessions {

    public static final String TABLE_NAME = "submitted_session";

    public static final String COLUMN_DISTRICT = "district";

    public static final String COLUMN_PROGRAMMATIC_AREA = "programmatic_area_id";

    public static final String COLUMN_TOTAL_SUBMITTED = "total_submitted";

    public static final String COLUMN_LAST_UPDATED = "last_updated";

    @DatabaseField(columnName = COLUMN_DISTRICT, canBeNull = false)
    private String district;

    @DatabaseField(columnName = COLUMN_PROGRAMMATIC_AREA, canBeNull = false, foreignAutoRefresh = true)
    private ProgrammaticArea programmaticArea;

    @DatabaseField(columnName = COLUMN_TOTAL_SUBMITTED, canBeNull = false)
    private Long totalSubmitted;

    @DatabaseField(columnName = COLUMN_LAST_UPDATED, canBeNull = false)
    private Calendar lastUpdate;

    public SubmittedSessions() {
    }

    public SubmittedSessions(String district, ProgrammaticArea programmaticArea, Long totalSubmitted, Calendar lastUpdate) {
        this.district = district;
        this.programmaticArea = programmaticArea;
        this.totalSubmitted = totalSubmitted;
        this.lastUpdate = lastUpdate;
    }
}
