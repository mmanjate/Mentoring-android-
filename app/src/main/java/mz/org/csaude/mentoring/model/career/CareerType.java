package mz.org.csaude.mentoring.model.career;

import static mz.org.csaude.mentoring.model.tutor.Tutor.COLUMN_TABLE_NAME;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.career.CareerDAOImpl;
import mz.org.csaude.mentoring.dao.career.CareerTypeDAOImpl;
import mz.org.csaude.mentoring.dao.tutor.TutorDAOImpl;
import mz.org.csaude.mentoring.dto.career.CareerTypeDTO;

@Data
@DatabaseTable(tableName = CareerType.COLUMN_TABLE_NAME, daoClass = CareerTypeDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class CareerType extends BaseModel implements Listble {

    public static final String COLUMN_TABLE_NAME = "career_type";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_CODE = "code";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE)
    private String code;
    public CareerType() {
        super();
    }

    public CareerType() {
    }

    public CareerType(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public CareerType(CareerTypeDTO careerTypeDTO){
        this.setUuid(careerTypeDTO.getUuid());
        this.setCode(careerTypeDTO.getCode());
        this.setDescription(careerTypeDTO.getDescription());
    }

    @Override
    public int getListPosition() {
        return 0;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getDrawable() {
        return 0;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
