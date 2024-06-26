package mz.org.csaude.mentoring.service.resource;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.resourceea.Resource;

public interface ResourceService extends BaseService<Resource> {

    void savedOrUpdateResource(Resource resource) throws SQLException;
    void savedOrUpdateResources(List<Resource> resources) throws SQLException;

}
