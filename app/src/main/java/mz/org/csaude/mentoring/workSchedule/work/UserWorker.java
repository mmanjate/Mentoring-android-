package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.user.UserSyncService;
import mz.org.csaude.mentoring.workSchedule.rest.UserRestService;

public class UserWorker extends BaseWorker<User> {

    private UserRestService userRestService;

    public UserWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.userRestService = new UserRestService((Application) getApplicationContext());
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        User user = new User(getInputData().getString("username"), getInputData().getString("password"));

        userRestService.getUserByCedencials(user);
    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<User> recs) {

    }
}
