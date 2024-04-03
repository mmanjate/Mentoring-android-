package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class HealthFacilityDAOImpl extends MentoringBaseDaoImpl<HealthFacility, Integer> implements HealthFacilityDAO{


    public HealthFacilityDAOImpl(Class<HealthFacility> dataClass) throws SQLException {
        super(dataClass);
    }

    public HealthFacilityDAOImpl(ConnectionSource connectionSource, Class<HealthFacility> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public HealthFacilityDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<HealthFacility> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }


    @Override
    public boolean checkHealthFacilityExistance(String uuid) throws SQLException {

        List<HealthFacility> healthFacilities = this.queryForEq(BaseModel.COLUMN_UUID, uuid);
        return !healthFacilities.isEmpty();

    }

    @Override
    public List<HealthFacility> getHealthFacilityByDistrict(District district) throws SQLException {
        return this.queryForEq(HealthFacility.COLUMN_DISTRICT, district.getId());
    }

    @Override
    public List<HealthFacility> getHealthFacilityByDistrictAndMentor(District district, Tutor mentor) throws SQLException {
        List<String> uuids = new ArrayList<>();
        for (Location location : mentor.getEmployee().getLocations()) {
            uuids.add(location.getHealthFacility().getUuid());
        }

        return this.queryBuilder().where().eq(HealthFacility.COLUMN_DISTRICT, district.getId()).and().in(HealthFacility.COLUMN_UUID, uuids).query();
    }


}
