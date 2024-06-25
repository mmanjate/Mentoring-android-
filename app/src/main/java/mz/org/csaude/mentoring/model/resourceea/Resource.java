package mz.org.csaude.mentoring.model.resourceea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.resource.ResourceDaoImpl;

@DatabaseTable(tableName = Resource.TABLE_NAME, daoClass = ResourceDaoImpl.class)
public class Resource extends BaseModel implements Listble {

    public static final String TABLE_NAME = "resources";
    public static final String COLUMN_RESOURCE = "resource";

    @DatabaseField(columnName = COLUMN_RESOURCE, unique = true)
    private String resource;

    public Resource(String resource) {
        this.resource = resource;
    }

    public Resource() {
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
