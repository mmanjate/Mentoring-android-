package mz.org.csaude.mentoring.dto.programmaticArea;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

public class ProgrammaticAreaDTO extends BaseEntityDTO {
    private String code;
    private String description;
    private String name;
    public ProgrammaticAreaDTO(ProgrammaticArea programmaticArea) {
        super((programmaticArea));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
