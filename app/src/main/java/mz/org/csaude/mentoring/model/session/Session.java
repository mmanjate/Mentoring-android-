package mz.org.csaude.mentoring.model.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.session.SessionDAOImpl;
import mz.org.csaude.mentoring.model.ronda.Ronda;

@Data
@DatabaseTable(tableName = Session.TABLE_NAME, daoClass = SessionDAOImpl.class)
@EqualsAndHashCode(callSuper=true)
public class Session extends BaseModel {

    public static final String TABLE_NAME = "session";

    public static final String COLUMN_START_DATE = "start_date";

    public static final String COLUMN_END_DATE = "end_date";

    public static final String COLUMN_PERFORMED_DATE = "performed_date";

    public static final String COLUMN_STATUS = "session_status_id";

    public static final String COLUMN_RONDA = "ronda_id";

    @DatabaseField(columnName = COLUMN_START_DATE, canBeNull = false)
    private Date startDate;

    @DatabaseField(columnName = COLUMN_END_DATE, canBeNull = false)
    private Date endDate;

    @DatabaseField(columnName = COLUMN_PERFORMED_DATE)
    private Date performedDate;

    @DatabaseField(columnName = COLUMN_STATUS, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private SessionStatus status;

    @DatabaseField(columnName = COLUMN_RONDA, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Ronda ronda;

    public Session() {
    }

    public Session(Date startDate, Date endDate, Date performedDate, SessionStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.performedDate = performedDate;
        this.status = status;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(Date performedDate) {
        this.performedDate = performedDate;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

}
