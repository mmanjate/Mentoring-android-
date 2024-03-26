package mz.org.csaude.mentoring.dao.professionalCategoryDAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;

public class ProfessionalCategoryDAOImpl extends BaseDaoImpl<ProfessionalCategory, Integer> implements ProfessionalCategoryDAO{


    public ProfessionalCategoryDAOImpl(Class<ProfessionalCategory> dataClass) throws SQLException {
        super(dataClass);
    }

    public ProfessionalCategoryDAOImpl(ConnectionSource connectionSource, Class<ProfessionalCategory> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ProfessionalCategoryDAOImpl(ConnectionSource connectionSource, DatabaseTableConfig<ProfessionalCategory> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public boolean checkExisteProfessionalCategory(String uuid) throws SQLException {

        List<ProfessionalCategory> professionalCategorys = this.queryForEq("uuid", uuid);
        return professionalCategorys.isEmpty();
    }
}
