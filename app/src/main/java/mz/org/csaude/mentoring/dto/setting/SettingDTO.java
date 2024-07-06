package mz.org.csaude.mentoring.dto.setting;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.setting.Setting;

@Data
public class SettingDTO extends BaseEntityDTO {

    private String designation;

    private String value;

    private String description;

    private String type;

    private Boolean enabled;
    public SettingDTO() {
    }

    public SettingDTO(final Setting setting) {
        super(setting);
        this.setDesignation(setting.getDesignation());
        this.setDescription(setting.getDescription());
        this.setValue(setting.getValue());
        this.setType(setting.getType());
        this.setEnabled(setting.getEnabled());
    }


    public Setting getSetting() {
        return new Setting(this);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
