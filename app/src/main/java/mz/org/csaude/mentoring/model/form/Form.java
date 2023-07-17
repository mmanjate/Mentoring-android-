package mz.org.csaude.mentoring.model.form;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;

public class Form extends BaseModel {

    private String name;

    private String code;

    private String description;

    private ProgrammaticArea programmaticArea;

    private FormType formType;

    private Integer targetPatient;

    private Integer targetFile;

    private Partner partiner;

    public Form() {
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public ProgrammaticArea getProgrammaticArea() {
        return programmaticArea;
    }

    public FormType getFormType() {
        return formType;
    }

    public Integer getTargetPatient() {
        return targetPatient;
    }

    public Integer getTargetFile() {
        return targetFile;
    }

    public Partner getPartiner() {
        return partiner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgrammaticArea(ProgrammaticArea programmaticArea) {
        this.programmaticArea = programmaticArea;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public void setTargetPatient(Integer targetPatient) {
        this.targetPatient = targetPatient;
    }

    public void setTargetFile(Integer targetFile) {
        this.targetFile = targetFile;
    }

    public void setPartiner(Partner partiner) {
        this.partiner = partiner;
    }
}
