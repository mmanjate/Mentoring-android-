package mz.org.csaude.mentoring.model.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.session.SessionStatusDAOImpl;

@Data
@DatabaseTable(tableName = SessionStatus.TABLE_NAME, daoClass = SessionStatusDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class SessionStatus extends BaseModel {

    public static final String TABLE_NAME = "session_status";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    public SessionStatus() {
    }

    public SessionStatus(String description, String code) {
        this.description = description;
        this.code = code;
    }
}
