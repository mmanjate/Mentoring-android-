package mz.org.csaude.mentoring.dto.program;

import lombok.Data;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.program.Program;
@Data
public class ProgramDTO extends BaseEntityDTO {
    private String description;
    private String name;
    public ProgramDTO() {
        super();
    }
    public ProgramDTO(Program program) {
        super((program));
    }

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

    public Program getProgram() {
        Program program = new Program();
        program.setId(this.getId());
        program.setDescription(this.getDescription());
        program.setName(this.getName());
        program.setUuid(this.getUuid());
        program.setSyncStatus(this.getSyncSatus());
        program.setCreatedAt(this.getCreatedAt());
        program.setUpdatedAt(this.getUpdatedAt());
        return program;
    }
}
