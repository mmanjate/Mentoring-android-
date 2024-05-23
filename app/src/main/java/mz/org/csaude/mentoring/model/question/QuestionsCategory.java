package mz.org.csaude.mentoring.model.question;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.question.QuestionsCategoryDAOImpl;
import mz.org.csaude.mentoring.dto.question.QuestionCategoryDTO;

@Data
@DatabaseTable(tableName = QuestionsCategory.TABLE_NAME, daoClass = QuestionsCategoryDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class QuestionsCategory extends BaseModel {

    public static final String TABLE_NAME = "question_category";

    public static final String COLUMN_CATEGORY = "category";

    @DatabaseField(columnName = COLUMN_CATEGORY)
    private String category;

    public QuestionsCategory() {
    }

    public QuestionsCategory(String category) {
        this.category = category;
    }

    public QuestionsCategory(QuestionCategoryDTO questionCategory) {
        super(questionCategory);
        this.setCategory(questionCategory.getCategory());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
