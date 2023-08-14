package mz.org.csaude.mentoring.dao.setting;

import com.j256.ormlite.dao.Dao;

import mz.org.csaude.mentoring.model.setting.Setting;

import java.sql.SQLException;

public interface SettingDAO extends Dao<Setting, Integer> {
    public boolean checkSettingExistence(String uuid) throws SQLException;

}
