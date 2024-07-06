package mz.org.csaude.mentoring.model.career;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.career.CareerTypeDAOImpl;
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


    public CareerType(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public CareerType(CareerTypeDTO careerTypeDTO){
        super(careerTypeDTO);
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
