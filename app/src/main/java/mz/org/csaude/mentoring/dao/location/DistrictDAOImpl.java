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
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class DistrictDAOImpl extends MentoringBaseDaoImpl<District, Integer> implements DistrictDAO {

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

    @Override
    public List<District> getByProvinceAndMentor(Province province, Tutor mentor) throws SQLException {
        List<String> districtList = new ArrayList<>();
        for (Location location : mentor.getEmployee().getLocations()) {
            districtList.add(location.getDistrict().getUuid());
        }
        return queryBuilder().where().eq(District.COLUMN_PROVINCE, province.getId()).and().in(District.COLUMN_UUID, districtList).query();
    }


}
