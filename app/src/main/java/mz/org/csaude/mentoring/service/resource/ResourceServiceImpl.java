package mz.org.csaude.mentoring.service.resource;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.resource.ResourceDAO;
import mz.org.csaude.mentoring.model.resourceea.Resource;

public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    private ResourceDAO resourceDAO;
    public ResourceServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.resourceDAO = getDataBaseHelper().getResourceDAO();
    }

    @Override
    public void savedOrUpdateResource(Resource resource) throws SQLException {
          this.resourceDAO.create(resource);
    }

    @Override
    public void savedOrUpdateResources(List<Resource> resources) throws SQLException {
          for (Resource resource :resources){
              this.savedOrUpdateResource(resource);
          }
    }

    @Override
    public Resource save(Resource record) throws SQLException {

        this.resourceDAO.create(record);
        return record;
    }

    @Override
    public Resource update(Resource record) throws SQLException {

        this.resourceDAO.update(record);
        return record;
    }

    @Override
    public int delete(Resource record) throws SQLException {
        return this.resourceDAO.delete(record);
    }

    @Override
    public List<Resource> getAll() throws SQLException {
        return this.resourceDAO.queryForAll();
    }
    @Override
    public Resource getById(int id) throws SQLException {
        return this.resourceDAO.queryForId(id);
    }


}
