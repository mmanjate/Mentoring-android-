package mz.org.csaude.mentoring.model.rondatype;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.rondatype.RondaTypeDAOImpl;
import mz.org.csaude.mentoring.dao.session.SessionDAOImpl;
import mz.org.csaude.mentoring.dto.location.RondaTypeDTO;
import mz.org.csaude.mentoring.model.session.Session;


@DatabaseTable(tableName = RondaType.TABLE_NAME, daoClass = RondaTypeDAOImpl.class)
public class RondaType extends BaseModel {
    public static final String TABLE_NAME = "ronda_type";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE)
    private String code;
    public RondaType() {

    }
    public RondaType(RondaTypeDTO rondaTypeDTO) {
        this.setDescription(rondaTypeDTO.getDescription());
        this.setCode(rondaTypeDTO.getCode());
        this.setUuid(rondaTypeDTO.getUuid());
        this.setSyncStatus(rondaTypeDTO.getSyncSatus());
        this.setCreatedAt(rondaTypeDTO.getCreatedAt());
        this.setUpdatedAt(rondaTypeDTO.getUpdatedAt());
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
