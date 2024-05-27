package mz.org.csaude.mentoring.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.dto.programmaticArea.ProgrammaticAreaDTO;
import mz.org.csaude.mentoring.model.form.Form;
@Data
@NoArgsConstructor
public class FormDTO extends BaseEntityDTO {
    private String name;
    private String code;
    private String description;
    private int targetPatient;
    private int targetFile;
    private PartnerDTO partner;
    private ProgrammaticAreaDTO programmaticArea;
    public FormDTO(Form form) {
        super(form);
        if(form.getPartner()!=null) {
            this.setPartner(new PartnerDTO(form.getPartner()));
        }
        if(form.getProgrammaticArea()!=null) {
            this.setProgrammaticArea(new ProgrammaticAreaDTO(form.getProgrammaticArea()));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTargetPatient() {
        return targetPatient;
    }

    public void setTargetPatient(int targetPatient) {
        this.targetPatient = targetPatient;
    }

    public int getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(int targetFile) {
        this.targetFile = targetFile;
    }

    public PartnerDTO getPartner() {
        return partner;
    }

    public void setPartner(PartnerDTO partner) {
        this.partner = partner;
    }

    public ProgrammaticAreaDTO getProgrammaticArea() {
        return programmaticArea;
    }

    public void setProgrammaticArea(ProgrammaticAreaDTO programmaticArea) {
        this.programmaticArea = programmaticArea;
    }

    public Form getForm() {
        Form form = new Form();
        form.setUuid(this.getUuid());
        form.setCode(this.getCode());
        form.setDescription(this.getDescription());
        form.setName(this.getName());
        form.setTargetFile(this.getTargetFile());
        form.setTargetPatient(this.getTargetPatient());
        form.setCreatedAt(this.getCreatedAt());
        form.setUpdatedAt(this.getUpdatedAt());
        if(this.getPartner()!=null) {
            form.setPartner(this.getPartner().getPartner());
        }
        if(this.getProgrammaticArea()!=null) {
            form.setProgrammaticArea(this.getProgrammaticArea().getProgrammaticArea());
        }

        return form;
    }
}
