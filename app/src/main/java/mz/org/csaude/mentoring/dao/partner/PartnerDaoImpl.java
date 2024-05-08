package mz.org.csaude.mentoring.dao.partner;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.util.LifeCycleStatus;

public class PartnerDaoImpl extends MentoringBaseDaoImpl<Partner, Integer> implements PartnerDao {


    public PartnerDaoImpl(Class<Partner> dataClass) throws SQLException {
        super(dataClass);
    }

    public PartnerDaoImpl(ConnectionSource connectionSource, Class<Partner> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public PartnerDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Partner> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public Partner getMISAU() throws SQLException {
        return queryBuilder().where().eq(Partner.COLUMN_UUID, Partner.MISAU_UUID).queryForFirst();
    }

    @Override
    public List<Partner> getNotMISAU() throws SQLException {
        return queryBuilder().where().ne(Partner.COLUMN_UUID, Partner.MISAU_UUID).and().eq(BaseModel.COLUMN_LIFE_CYCLE_STATUS, LifeCycleStatus.ACTIVE).query();
    }
}
