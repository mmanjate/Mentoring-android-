package mz.org.csaude.mentoring.model.mentorship;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.mentorship.IterationTypeDAOImpl;

@Data
@DatabaseTable(tableName = IterationType.TABLE_NAME, daoClass = IterationTypeDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class IterationType extends BaseModel {

    public static final String TABLE_NAME = "iteration_type";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    public IterationType() {
    }

    public IterationType(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
