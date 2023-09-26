package mz.org.csaude.mentoring.workSchedule.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.workSchedule.rest.TutorRestService;
import mz.org.csaude.mentoring.workSchedule.rest.TutoredRestService;

public class TutorWorker extends BaseWorker<Tutor> {
    public TutorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {

        TutorRestService.restGetTutor(offset, limit,this);
    }
    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<Tutor> recs) {

    }
}
