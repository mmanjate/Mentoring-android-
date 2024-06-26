package mz.org.csaude.mentoring.dao.resource;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.dao.MentoringBaseDao;
import mz.org.csaude.mentoring.model.resourceea.Resource;

public interface ResourceDAO extends MentoringBaseDao<Resource, Integer> {
    public boolean checkResourceExistance(final String uuid) throws SQLException;

}
