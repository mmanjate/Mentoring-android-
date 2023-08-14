package mz.org.csaude.mentoring.model.setting;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.setting.SettingDAO;
import mz.org.csaude.mentoring.dao.setting.SettingDAOImpl;
import mz.org.csaude.mentoring.dao.user.UserDaoImpl;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;

@Data
@DatabaseTable(tableName = Setting.TABLE_NAME, daoClass = SettingDAOImpl.class)
public class Setting extends BaseModel {

    public static final String TABLE_NAME = "setting";

    public static final String COLUMN_DESIGNATION = "designation";

    public static final String COLUMN_VALUE = "value";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_ENABLED = "enabled";

    @DatabaseField(columnName = COLUMN_DESIGNATION, unique = true)
    private String designation;

    @DatabaseField(columnName = COLUMN_VALUE, canBeNull = false)
    private String value;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_TYPE)
    private String type;

    @DatabaseField(columnName = COLUMN_ENABLED)
    private Boolean enabled;

    public Setting() {
    }

    public Setting(SettingDTO dto) {
        this.setDesignation(dto.getDescription());
        this.setDesignation(dto.getDesignation());
        this.setValue(dto.getValue());
        this.setType(dto.getType());
        this.setEnabled(dto.getEnabled());
    }

}
