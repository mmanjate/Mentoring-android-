package mz.org.csaude.mentoring.model.partner;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.partner.PartnerDaoImpl;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;


@DatabaseTable(tableName = Partner.TABLE_NAME, daoClass = PartnerDaoImpl.class)
public class Partner extends BaseModel implements Listble {

    public static final String TABLE_NAME = "partner";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_DESCRIPTION = "description";
    public static final String MISAU_UUID = "398f0ffeb8fe11edafa10242ac120002";

    @DatabaseField(columnName = COLUMN_NAME, unique = true)
    private String name;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    private String description;

    public Partner() {
    }

    public Partner(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Partner(PartnerDTO partnerDTO) {
        this.setUuid(partnerDTO.getUuid());
        this.name = partnerDTO.getName();
        this.description = partnerDTO.getDescription();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getDrawable() {
        return 0;
    }

    @Override
    public String getCode() {
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
