package mz.org.csaude.mentoring.model.responseType;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.responseType.ResponseTypeDAOImpl;
import mz.org.csaude.mentoring.dto.responseType.ResponseTypeDTO;

@Data
@DatabaseTable(tableName = ResponseType.TABLE_NAME, daoClass = ResponseTypeDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class ResponseType extends BaseModel {

    public static final String TABLE_NAME = "response_type";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    public ResponseType() {
    }

    public ResponseType(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public ResponseType(ResponseTypeDTO responseType) {
        super(responseType);
        this.setCode(responseType.getCode());
        this.setDescription(responseType.getDescription());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
