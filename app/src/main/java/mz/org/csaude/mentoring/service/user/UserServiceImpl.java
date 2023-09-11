package mz.org.csaude.mentoring.service.user;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.model.user.User;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    public UserServiceImpl(Application application) {
        super(application);
    }

    @Override
    public User save(User record) throws SQLException {
        return null;
    }

    @Override
    public User update(User record) throws SQLException {
        return null;
    }

    @Override
    public int delete(User record) throws SQLException {
        return 0;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public User getById(int id) throws SQLException {
        return null;
    }

    @Override
    public User login(User user) throws SQLException {
        // login online
        // login offline
        return null;
    }
}
