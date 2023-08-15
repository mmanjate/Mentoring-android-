package mz.org.csaude.mentoring.model.session;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.session.SessionDAOImpl;

@Data
@DatabaseTable(tableName = Session.TABLE_NAME, daoClass = SessionDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class Session extends BaseModel {

    public static final String TABLE_NAME = "session";

    public static final String COLUMN_START_DATE = "start_date";

    public static final String COLUMN_END_DATE = "end_date";

    public static final String COLUMN_PERFORMED_DATE = "performed_date";

    public static final String COLUMN_STATUS = "session_status_id";

    public static final String COLUMN_REASON = "reason";

    @DatabaseField(columnName = COLUMN_START_DATE, canBeNull = false)
    private Date startDate;

    @DatabaseField(columnName = COLUMN_END_DATE, canBeNull = false)
    private Date endDate;

    @DatabaseField(columnName = COLUMN_PERFORMED_DATE)
    private Date performedDate;

    @DatabaseField(columnName = COLUMN_STATUS, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private SessionStatus status;

    @DatabaseField(columnName = COLUMN_REASON)
    private String reason;

    public Session() {
    }

    public Session(Date startDate, Date endDate, Date performedDate, SessionStatus status, String reason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.performedDate = performedDate;
        this.status = status;
        this.reason = reason;
    }
}
