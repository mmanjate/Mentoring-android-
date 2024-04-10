package mz.org.csaude.mentoring.dao.tutored;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Location;
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

        return tutoredQb.orderBy(Employee.COLUMN_NAME, true).query();
    }

    @Override
    public List<Tutored> getAllNotSynced() throws SQLException {
        return queryForEq(Tutored.COLUMN_SYNC_STATUS, SyncSatus.PENDING);
    }
}
