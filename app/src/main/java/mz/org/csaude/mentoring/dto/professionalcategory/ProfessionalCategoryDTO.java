package mz.org.csaude.mentoring.dto.professionalcategory;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;

@NoArgsConstructor
public class ProfessionalCategoryDTO implements Serializable {

    private String uuid;
    private String code;
    private String description;

    public ProfessionalCategoryDTO(ProfessionalCategory professionalCategory){
        this.setUuid(professionalCategory.getUuid());
        this.setCode(professionalCategory.getCode());
        this.setDescription(professionalCategory.getDescription());
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
