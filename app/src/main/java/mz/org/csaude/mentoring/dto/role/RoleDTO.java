package mz.org.csaude.mentoring.dto.role;


import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;

public class RoleDTO extends BaseEntityDTO {

    private String description;

    private  String code;

    private  String level;


    private  int hierarchyLevel;

    public RoleDTO() {
    }

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(int hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }
}
