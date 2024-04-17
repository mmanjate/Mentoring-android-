package mz.org.csaude.mentoring.dao.user;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.util.Utilities;


public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

    public UserDaoImpl(Class<User> dataClass) throws SQLException {
        super(dataClass);
    }

    public UserDaoImpl(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public UserDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<User> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public User getByCredentials(User user) throws SQLException {
        return queryBuilder().where().eq(User.COLUMN_USER_NAME, user.getUserName()).and().eq(User.COLUMN_PASSWORD, Utilities.MD5Crypt(user.getPassword())).queryForFirst();
    }
}
