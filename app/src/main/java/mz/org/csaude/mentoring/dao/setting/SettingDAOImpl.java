package mz.org.csaude.mentoring.dao.setting;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.setting.Setting;

public class SettingDAOImpl extends BaseDaoImpl<Setting, Integer> implements SettingDAO {

    protected SettingDAOImpl(Class<Setting> dataClass) throws SQLException {
        super(dataClass);
    }

    protected SettingDAOImpl(ConnectionSource connectionSource, Class<Setting> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected SettingDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Setting> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
