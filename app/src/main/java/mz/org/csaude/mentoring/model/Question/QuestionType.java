package mz.org.csaude.mentoring.model.Question;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.question.QuestionTypeDAOImpl;

@Data
@DatabaseTable(tableName = QuestionType.TABLE_NAME, daoClass = QuestionTypeDAOImpl.class)
public class QuestionType extends BaseModel {

    public static final String TABLE_NAME = "question_type";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    public QuestionType() {
    }

    public QuestionType(String description, String code) {
        this.description = description;
        this.code = code;
    }
}
