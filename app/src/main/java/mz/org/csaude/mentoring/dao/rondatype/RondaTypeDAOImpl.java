package mz.org.csaude.mentoring.dao.rondatype;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDaoImpl;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

public class RondaTypeDAOImpl extends MentoringBaseDaoImpl<RondaType, Integer> implements RondaTypeDAO {
    public RondaTypeDAOImpl(Class<RondaType> dataClass) throws SQLException {
        super(dataClass);
    }

    public RondaTypeDAOImpl(ConnectionSource connectionSource, Class<RondaType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public RondaTypeDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<RondaType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public RondaType getRondaTypeByCode(String code) throws SQLException {
        return queryForEq(RondaType.COLUMN_CODE, code).get(0);
    }

    @Override
    public boolean checkRondaTypeExistance(String uuid) throws SQLException {
        List<RondaType> rondaTypes = this.queryForEq(BaseModel.COLUMN_UUID, uuid);
        return !rondaTypes.isEmpty();
    }
}
