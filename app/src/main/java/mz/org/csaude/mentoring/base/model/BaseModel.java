package mz.org.csaude.mentoring.base.model;

import com.j256.ormlite.field.DatabaseField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseModel implements Serializable {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_UUID = "uuid";

    @DatabaseField(columnName = COLUMN_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = COLUMN_UUID, unique = true)
    private String uuid;
}
