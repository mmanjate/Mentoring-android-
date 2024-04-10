package mz.org.csaude.mentoring.workSchedule.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.workSchedule.rest.TutoredRestService;

public class TutoredWork extends BaseWorker<Tutored> {
    public TutoredWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        getApplication().getTutoredRestService().restGetTutored(this);
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    protected void doAfterSearch(String flag, List<Tutored> recs) throws SQLException {
        changeStatusToFinished();
        doOnFinish();
    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<Tutored> recs) {

    }
}
