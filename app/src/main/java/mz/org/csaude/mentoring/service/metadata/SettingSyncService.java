package mz.org.csaude.mentoring.service.metadata;

import mz.org.csaude.mentoring.dto.setting.SettingDTO;

import java.util.List;

/**
 * Created by ialuj
 */
public interface SettingSyncService {
    public void processSettings(List<SettingDTO> settings);
}
