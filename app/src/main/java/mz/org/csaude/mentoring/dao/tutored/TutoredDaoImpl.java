package mz.org.csaude.mentoring.dao.tutored;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.LifeCycleStatus;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.workSchedule.work.MentoringDataBaseHelper;

public class TutoredDaoImpl extends MentoringBaseDaoImpl<Tutored, Integer> implements TutoredDao {

    public TutoredDaoImpl(Class<Tutored> dataClass) throws SQLException {
        super(dataClass);
    }

    public TutoredDaoImpl(ConnectionSource connectionSource, Class<Tutored> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public TutoredDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Tutored> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkTutoredExistance(String uuid) throws SQLException {
        List<Tutored> tutoreds = this.queryForEq("uuid", uuid);
        return !tutoreds.isEmpty();
    }

    @Override
    public List<Tutored> getAllOfHealthFacility(HealthFacility healthFacility, MentoringApplication application) throws SQLException {
        QueryBuilder<Location, Integer> locationQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getLocationDAO().queryBuilder();
        locationQb.where().eq(Location.COLUMN_HEALTH_FACILITY, healthFacility.getId()).and().eq(Location.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE);

        QueryBuilder<Employee, Integer> employeeQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getEmployeeDAO().queryBuilder();
        employeeQb.join(locationQb);

        QueryBuilder<Tutored, Integer> tutoredQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getTutoredDao().queryBuilder();
        tutoredQb.join(employeeQb).where().eq(Tutored.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE);

        return tutoredQb.orderBy(Employee.COLUMN_ID, true).query();
    }

    @Override
    public List<Tutored> getAllOfHealthFacilityForNewRonda(HealthFacility healthFacility, MentoringApplication application) throws SQLException {
        QueryBuilder<Location, Integer> locationQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getLocationDAO().queryBuilder();
        locationQb.where().eq(Location.COLUMN_HEALTH_FACILITY, healthFacility.getId()).and().eq(Location.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE);

        QueryBuilder<Employee, Integer> employeeQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getEmployeeDAO().queryBuilder();
        employeeQb.join(locationQb);

        QueryBuilder<Tutored, Integer> tutoredQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getTutoredDao().queryBuilder();
        tutoredQb.join(employeeQb).where().eq(Tutored.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE).and().eq(Tutored.COLUMN_ZERO_EVALUATION_STATUS, true);

        return tutoredQb.orderBy(Employee.COLUMN_ID, true).query();
    }

    @Override
    public List<Tutored> getAllNotSynced() throws SQLException {
        return queryForEq(Tutored.COLUMN_SYNC_STATUS, SyncSatus.PENDING);
    }

    @Override
    public List<Tutored> getAllOfRonda(Ronda currRonda, MentoringApplication application) throws SQLException {
        List<Tutored> tutoredList = new ArrayList<>();
            QueryBuilder<RondaMentee, Integer> rondaMenteeQb = MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getRondaMenteeDAO().queryBuilder();
            rondaMenteeQb.where().eq(RondaMentee.COLUMN_RONDA, currRonda.getId());

            List<RondaMentee> rondaMentees = rondaMenteeQb.query();
            for (RondaMentee rondaMentee : rondaMentees) {
                tutoredList.add(this.queryForId(rondaMentee.getTutored().getId()));
            }
        return tutoredList;
    }

    @Override
    public List<Tutored> getAllOfRondaForZeroEvaluation(Ronda currRonda, MentoringApplication application) throws SQLException {
        List<Tutored> tutoredList = new ArrayList<>();
        QueryBuilder<RondaMentee, Integer> rondaMenteeQb = MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getRondaMenteeDAO().queryBuilder();
        rondaMenteeQb.where().eq(RondaMentee.COLUMN_RONDA, currRonda.getId());

        List<RondaMentee> rondaMentees = rondaMenteeQb.query();
        for (RondaMentee rondaMentee : rondaMentees) {
            if (!rondaMentee.getTutored().isZeroEvaluationDone()) tutoredList.add(this.queryForId(rondaMentee.getTutored().getId()));
        }
        return tutoredList;
    }

    @Override
    public List<Tutored> getAllForMentoringRound(HealthFacility healthFacility, boolean zeroEvaluation, MentoringApplication application) throws SQLException {
        QueryBuilder<Location, Integer> locationQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getLocationDAO().queryBuilder();
        locationQb.where().eq(Location.COLUMN_HEALTH_FACILITY, healthFacility.getId()).and().eq(Location.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE);

        QueryBuilder<Employee, Integer> employeeQb =  MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getEmployeeDAO().queryBuilder();
        employeeQb.join(locationQb);

        QueryBuilder<Tutored, Integer> tutoredQb = MentoringDataBaseHelper.getInstance(application.getApplicationContext()).getTutoredDao().queryBuilder();

        Where<Tutored, Integer> where = tutoredQb.join(employeeQb)
                .where()
                .eq(Tutored.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE)
                .and()
                .eq(Tutored.COLUMN_ZERO_EVALUATION_STATUS, zeroEvaluation);

        if (zeroEvaluation) {
            where.and().isNotNull(Tutored.COLUMN_ZERO_EVALUATION_SCORE);
        }

        return tutoredQb.orderBy(Employee.COLUMN_ID, true).query();
    }
}
