package mz.org.csaude.mentoring.model.tutored;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.org.csaude.mentoring.base.model.BaseModel;

public class Tutored extends BaseModel {

    private String name;

    public Tutored() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
