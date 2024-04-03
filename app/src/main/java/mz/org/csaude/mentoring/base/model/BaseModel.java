package mz.org.csaude.mentoring.base.model;

import com.j256.ormlite.field.DatabaseField;
import lombok.Getter;
import lombok.Setter;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.util.LifeCycleStatus;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseModel implements Serializable {

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_UUID = "uuid";

    public static final String COLUMN_LIFE_CYCLE_STATUS = "life_cycle_status";

    @DatabaseField(columnName = COLUMN_ID, canBeNull = false, generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = COLUMN_UUID, unique = true)
    private String uuid;

    public BaseModel() {
    }

    public BaseModel(String uuid) {
        this.uuid = uuid;
    }

    public BaseModel(BaseEntityDTO baseEntityDTO) {
        this.uuid = baseEntityDTO.getUuid();
    }

    @DatabaseField(columnName = COLUMN_LIFE_CYCLE_STATUS)
    private LifeCycleStatus lifeCycleStatus;

    protected int listPosition;

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

    public String validade() {
        return null;
    }
}
