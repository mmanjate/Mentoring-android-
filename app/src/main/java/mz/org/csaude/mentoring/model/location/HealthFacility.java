package mz.org.csaude.mentoring.model.location;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.location.HealthFacilityDAOImpl;
import mz.org.csaude.mentoring.dto.location.HealthFacilityDTO;


@DatabaseTable(tableName = HealthFacility.COLUMN_TABLE_NAME, daoClass = HealthFacilityDAOImpl.class)
public class HealthFacility extends BaseModel implements Listble {
    public static final String COLUMN_TABLE_NAME = "health_facility";
    public static final String COLUMN_DISTRICT = "district_id";
    public static final String COLUMN_NAME = "name";
    @DatabaseField(columnName = COLUMN_DISTRICT, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private District district;

    public HealthFacility(HealthFacilityDTO healthFacilityDTO) {
        this.setUuid(healthFacilityDTO.getUuid());
        this.setDescription(healthFacilityDTO.getHealthFacility());
        if (healthFacilityDTO.getDistrictDTO() != null) this.setDistrict( new District(healthFacilityDTO.getDistrictDTO()));
    }

    public HealthFacility() {
    }

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getDescription() {
        return name;
    }
    public void setDescription(String name) {
        this.name = name;
    }

    @Override
    public int getDrawable() {
        return 0;
    }

    @Override
    public String getCode() {
        return null;
    }
}
