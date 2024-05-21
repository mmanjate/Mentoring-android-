package mz.org.csaude.mentoring.base.dto;

import java.io.Serializable;
import java.util.Date;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.util.LifeCycleStatus;
import mz.org.csaude.mentoring.util.SyncSatus;

public class BaseEntityDTO implements Serializable {


    private String uuid;

    private LifeCycleStatus lifeCycleStatus;

    private SyncSatus syncSatus = SyncSatus.PENDING;
    private Date createdAt;
    private Date updatedAt;

    public BaseEntityDTO() {
    }

    public BaseEntityDTO(BaseModel baseEntity) {
        this.setUuid(baseEntity.getUuid());
        this.setLifeCycleStatus(baseEntity.getLifeCycleStatus());
        this.setCreatedAt(baseEntity.getCreatedAt());
        this.setUpdatedAt(baseEntity.getUpdatedAt());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LifeCycleStatus getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
