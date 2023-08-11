package mz.org.csaude.mentoring.dao.setting;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import lombok.Getter;
import mz.org.csaude.mentoring.model.setting.Setting;

public class SettingDAOImpl extends BaseDaoImpl<Setting, Integer> implements SettingDAO {

    private static SettingDAOImpl instance;

    protected SettingDAOImpl(Class<Setting> dataClass) throws SQLException {
        super(dataClass);
    }

    protected SettingDAOImpl(ConnectionSource connectionSource, Class<Setting> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected SettingDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<Setting> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public static SettingDAOImpl getInstance(ConnectionSource connectionSource) {
        if (instance == null) {
            try {
                instance = new SettingDAOImpl(connectionSource, Setting.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public boolean checkSettingExistance(String uuid) throws SQLException {
        List<Setting> settings = this.queryForEq("uuid", uuid);
        return !settings.isEmpty();
    }
}
