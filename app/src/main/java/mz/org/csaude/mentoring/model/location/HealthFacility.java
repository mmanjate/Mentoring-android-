package mz.org.csaude.mentoring.model.location;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.career.CareerDAOImpl;
import mz.org.csaude.mentoring.dao.location.HealthFacilityDAOImpl;
import mz.org.csaude.mentoring.model.career.CareerType;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = HealthFacility.COLUMN_TABLE_NAME, daoClass = HealthFacilityDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class HealthFacility extends BaseModel {

    public static final String COLUMN_TABLE_NAME = "health_facility";
    public static final String COLUMN_DISTRICT = "district_id";
    public static final String COLUMN_NAME = "name";
    @DatabaseField(columnName = COLUMN_DISTRICT, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private District district;

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
