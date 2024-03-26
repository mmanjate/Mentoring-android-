package mz.org.csaude.mentoring.dao.partnerSetting;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.setting.PartnerSetting;

public class PartnerSettingDaoImpl extends BaseDaoImpl<PartnerSetting, Integer> implements PartnerSettingDao {

    public PartnerSettingDaoImpl(Class<PartnerSetting> dataClass) throws SQLException {
        super(dataClass);
    }

    public PartnerSettingDaoImpl(ConnectionSource connectionSource, Class<PartnerSetting> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public PartnerSettingDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<PartnerSetting> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
