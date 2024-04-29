package mz.org.csaude.mentoring.base.dto;

import java.io.Serializable;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.util.LifeCycleStatus;
import mz.org.csaude.mentoring.util.SyncSatus;

public class BaseEntityDTO implements Serializable {
    private Integer id;

    private String uuid;

    private LifeCycleStatus lifeCycleStatus;

    private SyncSatus syncSatus = SyncSatus.PENDING;

    public BaseEntityDTO() {
    }

    public BaseEntityDTO(BaseModel baseEntity) {
        this.setId(baseEntity.getId());
        this.setUuid(baseEntity.getUuid());
        this.setLifeCycleStatus(baseEntity.getLifeCycleStatus());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SyncSatus getSyncSatus() {
        return syncSatus;
    }

    public void setSyncSatus(SyncSatus syncSatus) {
        this.syncSatus = syncSatus;
    }
}
