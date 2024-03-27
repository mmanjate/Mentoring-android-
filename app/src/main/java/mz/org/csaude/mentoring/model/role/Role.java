package mz.org.csaude.mentoring.model.role;

import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.autority.Authority;


public class Role extends BaseModel {
    public static final String TABLE_NAME = "role";

    public static final String COLUMN_MODULE = "name";
    public static final String COLUMN_DESCRIPTION = "module";
    public static final String COLUMN_CODE = "code";

}
