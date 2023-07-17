package mz.org.csaude.mentoring.dao.mentorship;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public class MentorshipDAOImpl extends BaseDaoImpl<Mentorship, Integer> implements MentorshipDAO{

    protected MentorshipDAOImpl(Class<Mentorship> dataClass) throws SQLException {
        super(dataClass);
    }

    protected MentorshipDAOImpl(ConnectionSource connectionSource, Class<Mentorship> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected MentorshipDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Mentorship> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
