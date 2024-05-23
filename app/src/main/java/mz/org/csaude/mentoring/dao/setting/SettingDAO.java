package mz.org.csaude.mentoring.dao.setting;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.setting.Setting;

public interface SettingDAO extends Dao<Setting, Integer> {
    public boolean checkSettingExistence(String uuid) throws SQLException;

}
