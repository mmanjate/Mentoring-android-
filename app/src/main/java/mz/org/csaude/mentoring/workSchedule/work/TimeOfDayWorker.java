package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.mentorship.TimeOfDay;
import mz.org.csaude.mentoring.workSchedule.rest.TimeOfDayRestService;

public class TimeOfDayWorker extends BaseWorker<TimeOfDay> {
    private TimeOfDayRestService timeOfDayRestService;
    public TimeOfDayWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.timeOfDayRestService = new TimeOfDayRestService((Application) getApplicationContext());
    }
    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        this.timeOfDayRestService.restGetTimesOfDay(this);
    }

    @Override
    protected void doAfterSearch(String flag, List<TimeOfDay> recs) throws SQLException {
        changeStatusToFinished();
        doOnFinish();
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<TimeOfDay> recs) {

    }
}
