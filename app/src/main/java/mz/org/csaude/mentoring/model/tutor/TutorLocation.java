package mz.org.csaude.mentoring.model.tutor;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.tutor.TutorLocationDAOImpl;
import mz.org.csaude.mentoring.model.location.HealthFacility;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = TutorLocation.COLUMN_TABLE_NAME, daoClass = TutorLocationDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class TutorLocation extends BaseModel {

    public static final String COLUMN_TABLE_NAME = "tutor_location";
    public static final String COLUMN_TUTOR = "tutor_id";
    public static final String COLUMN_HEALTHFACILITY = "health_facility_id";

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_HEALTHFACILITY, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private HealthFacility healthFacility;


    public Tutor getTutor() {
        return tutor;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }
}
