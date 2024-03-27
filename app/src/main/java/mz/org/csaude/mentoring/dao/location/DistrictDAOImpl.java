package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Province;

public class DistrictDAOImpl extends BaseDaoImpl<District, Integer> implements DistrictDAO {

    public DistrictDAOImpl(Class<District> dataClass) throws SQLException {
        super(dataClass);
    }

    public DistrictDAOImpl(ConnectionSource connectionSource, Class<District> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public DistrictDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<District> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkDistrictxistance(String uuid) throws SQLException {

        List<District> districts = this.queryForEq(BaseModel.COLUMN_UUID, uuid);
        return !districts.isEmpty();
    }

    @Override
    public List<District> getByProvince(Province province) throws SQLException {
        return queryBuilder().where().eq(District.COLUMN_PROVINCE, province.getId()).query();
    }


}
