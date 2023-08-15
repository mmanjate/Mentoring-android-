package mz.org.csaude.mentoring.model.setting;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.partnerSetting.PartnerSettingDaoImpl;
import mz.org.csaude.mentoring.model.partner.Partner;

@Data
@DatabaseTable(tableName = PartnerSetting.TABLE_NAME, daoClass = PartnerSettingDaoImpl.class)
@EqualsAndHashCode(callSuper=false)
public class PartnerSetting extends BaseModel {

    public static final String TABLE_NAME = "partner_setting";

    public static final String COLUMN_PARTNER = "partner_id";

    public static final String COLUMN_SETTING = "setting_id";

    @DatabaseField(columnName = COLUMN_PARTNER, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Partner partner;

    @DatabaseField(columnName = COLUMN_SETTING, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Setting setting;

    public PartnerSetting() {
    }

    public PartnerSetting(Partner partner, Setting setting) {
        this.partner = partner;
        this.setting = setting;
    }
}
