package mz.org.csaude.mentoring.base.model;

import com.j256.ormlite.field.DatabaseField;
import lombok.Getter;
import lombok.Setter;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.util.LifeCycleStatus;
import mz.org.csaude.mentoring.util.SyncSatus;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class BaseModel implements Serializable {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_UUID = "uuid";

    public static final String COLUMN_LIFE_CYCLE_STATUS = "life_cycle_status";
    public static final String COLUMN_SYNC_STATUS = "sync_status";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    @DatabaseField(columnName = COLUMN_ID, canBeNull = false, generatedId = true, allowGeneratedIdInsert = true)
    private Integer id;

    @DatabaseField(columnName = COLUMN_UUID, unique = true)
    private String uuid;

    @DatabaseField(columnName = COLUMN_SYNC_STATUS)
    private SyncSatus syncStatus;
    @DatabaseField(columnName = COLUMN_CREATED_AT, canBeNull = true)
    private Date createdAt;
    @DatabaseField(columnName = COLUMN_UPDATED_AT, canBeNull = true)
    private Date updatedAt;

    public BaseModel() {
    }

    public BaseModel(String uuid) {
        this.uuid = uuid;
    }

    public BaseModel(BaseEntityDTO baseEntityDTO) {
        this.uuid = baseEntityDTO.getUuid();
    }

    @DatabaseField(columnName = COLUMN_LIFE_CYCLE_STATUS)
    private LifeCycleStatus lifeCycleStatus = LifeCycleStatus.ACTIVE;

    protected int listPosition;

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

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    public int getListPosition() {
        return listPosition;
    }

    public LifeCycleStatus getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public SyncSatus getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(SyncSatus syncStatus) {
        this.syncStatus = syncStatus;
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

    public String validade() {
        return null;
    }
}
