package mz.org.csaude.mentoring.model.indicator;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;



import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.indicator.IndicatorDAOImpl;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.tutor.Tutor;


@DatabaseTable(tableName = Indicator.TABLE_NAME, daoClass = IndicatorDAOImpl.class)

public class Indicator extends BaseModel {

    public static final String TABLE_NAME = "indicator";

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_PERFORMED_DATE = "performed_date";

    public static final String COLUMN_REFERRED_MONTH = "referred_month";

    public static final String COLUMN_TUTOR = "tutor_id";

    public static final String COLUMN_FORM = "form_id";

    public static final String COLUMN_HEALTH_FACILITY = "healthFacility_id";

    @DatabaseField(columnName = COLUMN_CODE)
    private String code;

    @DatabaseField(columnName = COLUMN_PERFORMED_DATE)
    private Date performedDate;

    @DatabaseField(columnName = COLUMN_REFERRED_MONTH)
    private Date referredMonth;

    @DatabaseField(columnName = COLUMN_TUTOR, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_FORM, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Form form;

    @DatabaseField(columnName = COLUMN_HEALTH_FACILITY, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private HealthFacility healthFacility;

    public Indicator() {
    }

    public Indicator(String code, Date performedDate, Date referredMonth, Tutor tutor, Form form, HealthFacility healthFacility) {
        this.code = code;
        this.performedDate = performedDate;
        this.referredMonth = referredMonth;
        this.tutor = tutor;
        this.form = form;
        this.healthFacility = healthFacility;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(Date performedDate) {
        this.performedDate = performedDate;
    }

    public Date getReferredMonth() {
        return referredMonth;
    }

    public void setReferredMonth(Date referredMonth) {
        this.referredMonth = referredMonth;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public HealthFacility getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(HealthFacility healthFacility) {
        this.healthFacility = healthFacility;
    }
}
