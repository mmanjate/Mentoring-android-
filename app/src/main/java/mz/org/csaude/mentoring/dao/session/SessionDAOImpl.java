package mz.org.csaude.mentoring.dao.session;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.databasehelper.MentoringDataBaseHelper;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class SessionDAOImpl extends MentoringBaseDaoImpl<Session, Integer> implements SessionDAO{

    public SessionDAOImpl(Class<Session> dataClass) throws SQLException {
        super(dataClass);
    }

    public SessionDAOImpl(ConnectionSource connectionSource, Class<Session> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SessionDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Session> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public List<Session> queryForAllOfRondaAndMentee(Ronda currRonda, Tutored selectedMentee, MentoringApplication application) {
        try {

            return this.queryBuilder().where()
                    .eq(Session.COLUMN_RONDA, currRonda.getId())
                    .and()
                    .eq(Session.COLUMN_MENTEE, selectedMentee.getId()).query();

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Session> queryForAllOfRondaAndMentor(Ronda currRonda, Tutored selectedMentor) {
        return null;
    }

    @Override
    public List<Session> queryForAllOfRonda(Ronda currRonda) {
        return null;
    }

    @Override
    public List<Session> queryForAllOfMentee(Tutored selectedMentee) {
        return null;
    }

    @Override
    public List<Session> queryForAllOfMentor(Tutored selectedMentor) {
        return null;
    }
}
