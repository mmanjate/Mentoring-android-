package mz.org.csaude.mentoring.service.setting;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.setting.SettingDAO;
import mz.org.csaude.mentoring.dto.setting.SettingDTO;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.model.user.User;

public class SettingServiceImpl extends BaseServiceImpl<Setting> implements SettingService {

    SettingDAO settingDAO;

    public SettingServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public SettingServiceImpl(Application application) {
        super(application, null);
        try {
            this.settingDAO = getDataBaseHelper().getSettingDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.settingDAO = getDataBaseHelper().getSettingDAO();
    }
    @Override
    public Setting save(Setting record) throws SQLException {
        this.settingDAO.create(record);
        return record;
    }

    @Override
    public Setting update(Setting record) throws SQLException {
        this.settingDAO.update(record);
        return record;
    }

    @Override
    public int delete(Setting record) throws SQLException {
        return this.settingDAO.delete(record);
    }

    @Override
    public List<Setting> getAll() throws SQLException {
        return this.settingDAO.queryForAll();
    }

    @Override
    public Setting getById(int id) throws SQLException {
        return this.settingDAO.queryForId(id);
    }

    @Override
    public void savedOrUpdateSettings(List<SettingDTO> settings) throws SQLException {
        for (SettingDTO settingDTO: settings) {
            boolean doesSettingExist = settingDAO.checkSettingExistence(settingDTO.getUuid());
            if(!doesSettingExist) {
                Setting setting = settingDTO.getSetting();
                this.settingDAO.createOrUpdate(setting);
            }
        }
    }
}
