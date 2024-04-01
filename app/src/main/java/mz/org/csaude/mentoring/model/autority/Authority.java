package mz.org.csaude.mentoring.model.autority;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.answer.AnswerTypeDAOImpl;
import mz.org.csaude.mentoring.model.answer.AnswerType;


public class Authority extends BaseModel {

    public static final String TABLE_NAME = "authority";

    public static final String COLUMN_MODULE = "name";
    public static final String COLUMN_DESCRIPTION = "module";
    public static final String COLUMN_CODE = "code";
    @DatabaseField(columnName = COLUMN_MODULE, canBeNull = false)
    private  String module;

    @DatabaseField(columnName = COLUMN_DESCRIPTION, canBeNull = false)
    private String description;

    @DatabaseField(columnName = COLUMN_MODULE, canBeNull = false)
    private  String code;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
