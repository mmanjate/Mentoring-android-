package mz.org.csaude.mentoring.dao.setting;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.setting.Setting;

public class SettingDAOImpl extends BaseDaoImpl<Setting, Integer> implements SettingDAO {

    private static SettingDAOImpl instance;

    public SettingDAOImpl(Class<Setting> dataClass) throws SQLException {
        super(dataClass);
    }

    public SettingDAOImpl(ConnectionSource connectionSource, Class<Setting> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SettingDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Setting> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkSettingExistence(String uuid) throws SQLException {
        List<Setting> settings = this.queryForEq(BaseModel.COLUMN_UUID, uuid);
        return !settings.isEmpty();
    }
}
