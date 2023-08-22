package mz.org.csaude.mentoring.model.career;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.career.CareerDAOImpl;
import mz.org.csaude.mentoring.dao.session.SessionStatusDAOImpl;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.model.session.SessionStatus;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = Career.TABLE_NAME, daoClass = CareerDAOImpl.class)
@EqualsAndHashCode(callSuper = false)
public class Career extends BaseModel {

    public static final String TABLE_NAME = "career";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_CAREER_TYPE = "career_type_id";

    @DatabaseField(columnName = COLUMN_CAREER_TYPE, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private CareerType careerType;

    @DatabaseField(columnName = COLUMN_POSITION)
    private String position;

    public Career(CareerDTO careerDTO) {
        this.setUuid(careerDTO.getUuid());
        this.setPosition(careerDTO.getPosition());
    }

    public CareerType getCareerType() {
        return careerType;
    }

    public void setCareerType(CareerType careerType) {
        this.careerType = careerType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
