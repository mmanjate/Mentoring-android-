package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.workSchedule.rest.CareerRestService;

public class CareerWorker extends BaseWorker<Career> {

    protected CareerRestService careerRestService;

    public CareerWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        careerRestService = new CareerRestService((Application) this.context.getApplicationContext());
    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        careerRestService.restGetCareers(offset, limit, this);
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<Career> recs) {

    }
}
