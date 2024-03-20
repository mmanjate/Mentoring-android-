package mz.org.csaude.mentoring.base.service;

import android.app.Application;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.databasehelper.MentoringDataBaseHelper;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.user.User;

public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T>{

    protected MentoringDataBaseHelper dataBaseHelper;

    protected Application application;
    public static Application app;
    protected User currentUser;

    public BaseServiceImpl(Application application) {
        try {
            init(application);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(Application application) throws SQLException {
        this.dataBaseHelper = MentoringDataBaseHelper.getInstance(application);
        this.application=application;
        BaseServiceImpl.app = application;
    }

    public MentoringDataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }

    public Application getApplication() {
        return application;
    }

    public static Application getApp() {
        return app;
    }

    public User getCurrentUser() {
        return ((MentoringApplication) this.application).getAuthenticatedUser();
    }
}
