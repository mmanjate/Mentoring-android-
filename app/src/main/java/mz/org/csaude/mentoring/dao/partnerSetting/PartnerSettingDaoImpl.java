package mz.org.csaude.mentoring.dao.partnerSetting;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.setting.PartnerSetting;

import java.sql.SQLException;

public class PartnerSettingDaoImpl extends BaseDaoImpl<PartnerSetting, Integer> implements PartnerSettingDao {

    protected PartnerSettingDaoImpl(Class<PartnerSetting> dataClass) throws SQLException {
        super(dataClass);
    }

    protected PartnerSettingDaoImpl(ConnectionSource connectionSource, Class<PartnerSetting> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected PartnerSettingDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<PartnerSetting> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
