package mz.org.csaude.mentoring.service.location;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.location.LocationDAO;
import mz.org.csaude.mentoring.dto.location.LocationDTO;
import mz.org.csaude.mentoring.dto.location.ProvinceDTO;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.Location;
import mz.org.csaude.mentoring.model.location.Province;

public class LocationServiceImpl extends BaseServiceImpl<Location> implements LocationService {


    private LocationDAO locationDAO;

    public LocationServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
     this.locationDAO = getDataBaseHelper().getLocationDAO();
    }
    @Override
    public Location save(Location record) throws SQLException {
        this.locationDAO.create(record);
        return record;
    }

    @Override
    public Location update(Location record) throws SQLException {
        this.locationDAO.update(record);
        return record;
    }

    @Override
    public int delete(Location record) throws SQLException {
        return this.locationDAO.delete(record);
    }

    @Override
    public List<Location> getAll() throws SQLException {
        return this.locationDAO.queryForAll();
    }

    @Override
    public Location getById(int id) throws SQLException {
        return this.locationDAO.queryForId(id);
    }
    
    @Override
    public void saveOrUpdates(List<LocationDTO> locationDTOS) throws SQLException {
        for (LocationDTO locationDTO : locationDTOS){
            this.saveOrUpdate(new Location(locationDTO));
        }
    }

    @Override
    public Location saveOrUpdate(Location location) throws SQLException {

        Location l = this.locationDAO.getByUuid(location.getUuid());

        if (l == null){
            this.locationDAO.create(location);
            return location;
        } else {
            location.setId(l.getId());
            this.locationDAO.update(location);
            return location;
        }
    }
}
