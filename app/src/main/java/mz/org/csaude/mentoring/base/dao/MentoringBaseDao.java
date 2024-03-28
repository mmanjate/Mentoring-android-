package mz.org.csaude.mentoring.base.dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface MentoringBaseDao<T, ID> extends Dao<T, ID> {

    T getByUuid(String uuid) throws SQLException;
}
