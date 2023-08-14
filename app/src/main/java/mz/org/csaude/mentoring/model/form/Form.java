package mz.org.csaude.mentoring.model.form;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.form.FormDAOImpl;
import mz.org.csaude.mentoring.dao.session.SessionStatusDAOImpl;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = Form.TABLE_NAME, daoClass = FormDAOImpl.class)
public class Form extends BaseModel {

    public static final String TABLE_NAME = "form";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PROGRAMMATIC_AREA= "programmatic_area_type_id";
    public static final String COLUMN_FORM_TYPE = "form_type_id";
    public static final String COLUMN_TARGET_PATIENT = "target_patient";
    public static final String COLUMN_TARGET_FILE = "target_file";
    public static final String COLUMN_PARTNER = "partner_id";

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;

    @DatabaseField(columnName = COLUMN_CODE)
    private String code;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_PROGRAMMATIC_AREA, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private ProgrammaticArea programmaticArea;

    @DatabaseField(columnName = COLUMN_FORM_TYPE, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private FormType formType;

    @DatabaseField(columnName = COLUMN_TARGET_PATIENT)
    private int targetPatient;

    @DatabaseField(columnName = COLUMN_TARGET_FILE)
    private int targetFile;

    @DatabaseField(columnName = COLUMN_PARTNER, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private Partner partner;


}
