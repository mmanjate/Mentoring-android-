package mz.org.csaude.mentoring.dao.formQuestion;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.databasehelper.MentoringDataBaseHelper;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.evaluationType.EvaluationType;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.util.LifeCycleStatus;

public class FormQuestionDAImpl extends MentoringBaseDaoImpl<FormQuestion, Integer> implements FormQuestionDAO {

    public FormQuestionDAImpl(Class<FormQuestion> dataClass) throws SQLException {
        super(dataClass);
    }

    public FormQuestionDAImpl(ConnectionSource connectionSource, Class<FormQuestion> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public FormQuestionDAImpl(ConnectionSource connectionSource, DatabaseTableConfig<FormQuestion> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<FormQuestion> getAllOfForm(Form form, String evaluationType, MentoringApplication application) throws SQLException {
        // Initialize the query builder for FormQuestion
        QueryBuilder<FormQuestion, Integer> queryBuilder = this.queryBuilder();

        // Initialize the query builder for EvaluationType
        QueryBuilder<EvaluationType, Integer> evaluationTypeQb = MentoringDataBaseHelper.getInstance(application)
                .getEvaluationTypeDAO()
                .queryBuilder();

        // Create the subquery for EvaluationType
        evaluationTypeQb.selectColumns(EvaluationType.COLUMN_ID)
                .where()
                .eq(EvaluationType.COLUMN_CODE, evaluationType);

        // Build the WHERE clause for FormQuestion
        Where<FormQuestion, Integer> where = queryBuilder.where();
        where.eq(FormQuestion.COLUMN_FORM, form.getId())
                .and()
                .eq(BaseModel.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE)
                .and()
                .in(FormQuestion.COLUMN_EVALUATION_TYPE, evaluationTypeQb);

        // Join with the Question table
        QueryBuilder<Question, Integer> questionQb = MentoringDataBaseHelper.getInstance(application)
                .getQuestionDAO()
                .queryBuilder();
        queryBuilder.join(questionQb);

        // Order by question_category_id and sequence
        queryBuilder.orderBy("sequence", true);

        // Execute the query and return the results
        return queryBuilder.query();
    }
}
