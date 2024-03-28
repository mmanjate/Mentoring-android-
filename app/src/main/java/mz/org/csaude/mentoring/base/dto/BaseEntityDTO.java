package mz.org.csaude.mentoring.base.dto;

import java.io.Serializable;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.util.LifeCycleStatus;

public class BaseEntityDTO implements Serializable {
    private int id;

    private String uuid;

    private LifeCycleStatus lifeCycleStatus;

    public BaseEntityDTO() {
    }

    public BaseEntityDTO(BaseModel baseEntity) {
        this.setId(baseEntity.getId());
        this.setUuid(baseEntity.getUuid());
        this.setLifeCycleStatus(baseEntity.getLifeCycleStatus());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
