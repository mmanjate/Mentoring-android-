package mz.org.csaude.mentoring.service.setting;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.model.setting.Setting;

public interface SettingService extends BaseService<Setting> {

    void savedOrUpdateSettings(List<SettingDTO> settings) throws SQLException;
}
