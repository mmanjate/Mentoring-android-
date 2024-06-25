package mz.org.csaude.mentoring.model.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.session.SessionReportDaoImpl;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.tutored.Tutored;

@DatabaseTable(tableName = SessionReport.TABLE_NAME, daoClass = SessionReportDaoImpl.class)
public class SessionReport extends BaseModel {

    public static final String TABLE_NAME = "session_report";
    public static final String COLUMN_SESSION_ID = "session_id";
    public static final String COLUMN_TUTORED_ID = "tutored_id";
    public static final String COLUMN_FORM_ID = "form_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_YES_POINTS = "yes_points";
    public static final String COLUMN_NO_POINTS = "no_points";
    public static final String COLUMN_SCORE = "score";

    @DatabaseField(columnName = COLUMN_SESSION_ID, canBeNull = false, foreign = true)
    private Session session;

    @DatabaseField(columnName = COLUMN_TUTORED_ID, canBeNull = false, foreign = true)
    private Tutored tutored;

    @DatabaseField(columnName = COLUMN_FORM_ID, canBeNull = false, foreign = true)
    private Form form;

    @DatabaseField(columnName = COLUMN_CATEGORY, canBeNull = false)
    private String category;

    @DatabaseField(columnName = COLUMN_YES_POINTS, canBeNull = false)
    private int yesPoints;

    @DatabaseField(columnName = COLUMN_NO_POINTS, canBeNull = false)
    private int noPoints;

    @DatabaseField(columnName = COLUMN_SCORE, canBeNull = false)
    private double score;

    // Default constructor is needed by ORMLite
    public SessionReport() {
    }

    // Getters and Setters
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Tutored getTutored() {
        return tutored;
    }

    public void setTutored(Tutored tutored) {
        this.tutored = tutored;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYesPoints() {
        return yesPoints;
    }

    public void setYesPoints(int yesPoints) {
        this.yesPoints = yesPoints;
    }

    public int getNoPoints() {
        return noPoints;
    }

    public void setNoPoints(int noPoints) {
        this.noPoints = noPoints;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
