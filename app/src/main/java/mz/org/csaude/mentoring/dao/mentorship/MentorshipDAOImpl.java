package mz.org.csaude.mentoring.dao.mentorship;

import android.app.Application;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.databasehelper.MentoringDataBaseHelper;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class MentorshipDAOImpl extends BaseDaoImpl<Mentorship, Integer> implements MentorshipDAO{

    public MentorshipDAOImpl(Class<Mentorship> dataClass) throws SQLException {
        super(dataClass);
    }

    public MentorshipDAOImpl(ConnectionSource connectionSource, Class<Mentorship> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public MentorshipDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Mentorship> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<Mentorship> getMentorshipByTutor(Application application, String uuidTutor) throws SQLException {

        QueryBuilder<Tutor, Integer> tutorQueryBuilder = MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getTutorDAO().queryBuilder();
        tutorQueryBuilder.where().eq(Tutor.COLUMN_UUID, uuidTutor);

        QueryBuilder<Mentorship, Integer> mentorshipQueryBuilder = MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getMentorshipDAO().queryBuilder();
        mentorshipQueryBuilder.where().eq(Mentorship.COLUMN_TUTOR, new ColumnArg(Tutor.COLUMN_TABLE_NAME, Tutor.COLUMN_ID));
        mentorshipQueryBuilder.join(tutorQueryBuilder);

        return mentorshipQueryBuilder.query();
    }
}
