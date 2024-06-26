package mz.org.csaude.mentoring.model.session;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.session.SessionRecommendedResourceDAOImpl;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.DateUtilities;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;

@DatabaseTable(tableName = SessionRecommendedResource.TABLE_NAME, daoClass = SessionRecommendedResourceDAOImpl.class)
public class SessionRecommendedResource extends BaseModel {

    public static final String TABLE_NAME = "session_recommended_resource";
    public static final String COLUMN_SESSION_ID = "session_id";
    public static final String COLUMN_TUTORED_ID = "tutored_id";
    public static final String COLUMN_TUTOR_ID = "tutor_id";
    public static final String COLUMN_RESOURCE_LINK = "resource_link";
    public static final String COLUMN_RESOURCE_NAME = "resource_name";
    public static final String COLUMN_DATE_RECOMMENDED = "date_recommended";

    @DatabaseField(columnName = COLUMN_SESSION_ID, canBeNull = false, foreign = true)
    private Session session;
    @DatabaseField(columnName = COLUMN_TUTORED_ID, canBeNull = false, foreign = true)
    private Tutored tutored;
    @DatabaseField(columnName = COLUMN_TUTOR_ID, canBeNull = false, foreign = true)
    private Tutor tutor;

    @DatabaseField(columnName = COLUMN_RESOURCE_LINK, canBeNull = false)
    private String resourceLink;
    @DatabaseField(columnName = COLUMN_RESOURCE_NAME, canBeNull = false)
    private String resourceName;

    @DatabaseField(columnName = COLUMN_DATE_RECOMMENDED, canBeNull = false)
    private Date dateRecommended;

    // Default constructor is needed by ORMLite
    public SessionRecommendedResource() {
    }

    public SessionRecommendedResource(Session session, Node node) {
        this.tutor = session.getRonda().getActiveMentor();
        this.tutored = session.getTutored();
        this.session = session;
        this.resourceLink = node.getName();
        this.resourceName = node.getName();
        this.dateRecommended = DateUtilities.getCurrentDate();
        this.setSyncStatus(SyncSatus.PENDING);
        this.setCreatedAt(DateUtilities.getCurrentDate());
        this.setUuid(Utilities.getNewUUID().toString());
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Date getDateRecommended() {
        return dateRecommended;
    }

    public void setDateRecommended(Date dateRecommended) {
        this.dateRecommended = dateRecommended;
    }
}