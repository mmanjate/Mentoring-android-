package mz.org.csaude.mentoring.model.form;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.form.FormDAOImpl;
import mz.org.csaude.mentoring.dao.form.FormTargetDAOImpl;
import mz.org.csaude.mentoring.model.career.Career;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = FormTarget.TABLE_NAME, daoClass = FormTargetDAOImpl.class)
@EqualsAndHashCode(callSuper=false)
public class FormTarget extends BaseModel {

    public static final String TABLE_NAME = "form_target";
    public static final String COLUMN_FORM= "form_id";
    public static final String COLUMN_CAREER = "career_id";
    public static final String COLUMN_TARGET = "target";


    @DatabaseField(columnName = COLUMN_FORM, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private Form form;

    @DatabaseField(columnName = COLUMN_CAREER, canBeNull = false, foreign = true, foreignAutoRefresh = true )
    private Career career;

    @DatabaseField(columnName = COLUMN_TARGET)
    private int target;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
