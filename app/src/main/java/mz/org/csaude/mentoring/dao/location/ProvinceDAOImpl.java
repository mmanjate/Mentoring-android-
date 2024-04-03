package mz.org.csaude.mentoring.dao.location;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public class ProvinceDAOImpl extends MentoringBaseDaoImpl<Province, Integer> implements ProvinceDAO {

    public ProvinceDAOImpl(Class<Province> dataClass) throws SQLException {
        super(dataClass);
    }

    public ProvinceDAOImpl(ConnectionSource connectionSource, Class<Province> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ProvinceDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Province> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }


    @Override
    public boolean checkProvinceExistance(String uuid) throws SQLException {
            List<Province> provinces = this.queryForEq("uuid", uuid);
            return !provinces.isEmpty();

    }

    @Override
    public List<Province> getAllOfTutor(Tutor tutor) throws SQLException {
        List<String> provinceList = new ArrayList<>();
        for (Location location : tutor.getEmployee().getLocations()) {
            provinceList.add(location.getProvince().getUuid());
        }
        return queryBuilder().where().in(Province.COLUMN_UUID, provinceList).query();
    }
}
