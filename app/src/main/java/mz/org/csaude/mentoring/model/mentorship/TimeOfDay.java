package mz.org.csaude.mentoring.model.mentorship;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.mentorship.TimeOfDayDAOImpl;

@Data
@DatabaseTable(tableName = TimeOfDay.TABLE_NAME, daoClass = TimeOfDayDAOImpl.class)
public class TimeOfDay extends BaseModel {

    public static final String TABLE_NAME = "time_of_day";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    public TimeOfDay() {
    }

    public TimeOfDay(String description, String code) {
        this.description = description;
        this.code = code;
    }
}
