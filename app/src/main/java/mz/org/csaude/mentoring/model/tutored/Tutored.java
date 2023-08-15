package mz.org.csaude.mentoring.model.tutored;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.*;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.tutored.TutoredDaoImpl;
import mz.org.csaude.mentoring.dao.user.UserDaoImpl;
import mz.org.csaude.mentoring.model.career.Career;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = Tutored.COLUMN_TABLE_NAME, daoClass = TutoredDaoImpl.class)
@EqualsAndHashCode(callSuper=false)
public class Tutored extends BaseModel {

    public static final String COLUMN_TABLE_NAME = "tutored";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PHONENUMBER = "phone_number";
    public static final String COLUMN_CAREER = "career_id";

    @DatabaseField(columnName = COLUMN_CODE)
    private String code;

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;

    @DatabaseField(columnName = COLUMN_SURNAME)
    private String surname;

    @DatabaseField(columnName = COLUMN_PHONENUMBER)
    private String phoneNumber;

    @DatabaseField(columnName = COLUMN_EMAIL)
    private String email;

    @DatabaseField(columnName = COLUMN_CAREER, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private Career career;
}
