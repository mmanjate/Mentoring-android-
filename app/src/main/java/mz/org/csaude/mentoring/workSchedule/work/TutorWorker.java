package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.tutor.TutorService;
import mz.org.csaude.mentoring.service.tutor.TutorServiceImpl;
import mz.org.csaude.mentoring.service.user.UserServiceImpl;
import mz.org.csaude.mentoring.workSchedule.rest.TutorRestService;
import mz.org.csaude.mentoring.workSchedule.rest.TutoredRestService;

public class TutorWorker extends BaseWorker<Tutor> {

    private final UserServiceImpl userService;
    private TutorRestService tutorRestService;

    private  User user;
    public TutorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.userService = new UserServiceImpl((Application) getApplicationContext());
    }

    @Override
    protected void doOnStart() {
        try {
            user = userService.getAll().get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.tutorRestService = new TutorRestService((Application) getApplicationContext(), user);
    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {

        this.tutorRestService.restGetTutoredUserUuid(this);
    }

    @Override
    protected void doAfterSearch(String flag, List<Tutor> recs) throws SQLException {
        changeStatusToFinished();
        doOnFinish();
    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<Tutor> recs) {

    }
}
