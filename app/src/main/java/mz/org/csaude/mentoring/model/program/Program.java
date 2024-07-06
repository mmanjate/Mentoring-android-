package mz.org.csaude.mentoring.model.program;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.program.ProgramDAOImpl;
import mz.org.csaude.mentoring.dto.program.ProgramDTO;

@Data
@DatabaseTable(tableName = Program.TABLE_NAME, daoClass = ProgramDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class Program extends BaseModel {

    public static final String TABLE_NAME = "program";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_DESCRIPTION = "description";
    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;
    @DatabaseField(columnName = COLUMN_NAME, canBeNull = false)
    private String name;

    public Program() {
        super();
    }

    public Program(ProgramDTO programDTO) {
        super(programDTO);
        this.setDescription(programDTO.getDescription());
        this.setName(programDTO.getName());
    }

    public Program(String description, String name) {
        this.description = description;
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
