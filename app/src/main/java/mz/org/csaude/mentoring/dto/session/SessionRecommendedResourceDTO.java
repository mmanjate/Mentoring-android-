package mz.org.csaude.mentoring.dto.session;

import java.util.Date;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.session.SessionRecommendedResource;

public class SessionRecommendedResourceDTO extends BaseEntityDTO {
    private String sessionUuid;
    private String tutoredUuid;
    private String tutorUuid;
    private String resourceLink;
    private String resourceName;
    private Date dateRecommended;

    public SessionRecommendedResourceDTO() {
    }

    public SessionRecommendedResourceDTO(SessionRecommendedResource resource) {
        super(resource);
        this.sessionUuid = resource.getSession() != null ? resource.getSession().getUuid() : null;
        this.tutoredUuid = resource.getTutored() != null ? resource.getTutored().getUuid() : null;
        this.tutorUuid = resource.getTutor() != null ? resource.getTutor().getUuid() : null;
        this.resourceLink = resource.getResourceLink();
        this.resourceName = resource.getResourceName();
        this.dateRecommended = resource.getDateRecommended();
    }

    public String getSessionUuid() {
        return sessionUuid;
    }

    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }

    public String getTutoredUuid() {
        return tutoredUuid;
    }

    public void setTutoredUuid(String tutoredUuid) {
        this.tutoredUuid = tutoredUuid;
    }

    public String getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(String tutorUuid) {
        this.tutorUuid = tutorUuid;
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
