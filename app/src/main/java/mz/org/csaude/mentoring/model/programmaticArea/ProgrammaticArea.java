package mz.org.csaude.mentoring.model.programmaticArea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.programmaticArea.ProgrammaticAreaDAOImpl;
import mz.org.csaude.mentoring.dto.programmaticArea.ProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.program.Program;

@Data
@DatabaseTable(tableName = ProgrammaticArea.TABLE_NAME, daoClass = ProgrammaticAreaDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class ProgrammaticArea extends BaseModel {

    public static final String TABLE_NAME = "programmatic_area";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_PROGRAM = "program_id";

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COLUMN_CODE, unique = true)
    private String code;

    @DatabaseField(columnName = COLUMN_NAME, canBeNull = false)
    private String name;
    @DatabaseField(columnName = COLUMN_PROGRAM, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Program program;

    public ProgrammaticArea() {
    }

    public ProgrammaticArea(ProgrammaticAreaDTO programmaticAreaDTO) {
        super(programmaticAreaDTO);
        this.setCode(programmaticAreaDTO.getCode());
        this.setDescription(programmaticAreaDTO.getDescription());
        this.setName(programmaticAreaDTO.getName());
        if (programmaticAreaDTO.getProgram() != null) this.program = new Program(programmaticAreaDTO.getProgram());
    }

    public ProgrammaticArea(String description, String code, String name, Program program) {
        this.description = description;
        this.code = code;
        this.name = name;
        this.program = program;
    }

    @Override
    public String getDescription() {
        return description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
