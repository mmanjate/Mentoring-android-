package mz.org.csaude.mentoring.workSchedule.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.workSchedule.rest.CabinetRestService;
import mz.org.csaude.mentoring.workSchedule.rest.CareerRestService;

public class CabinetWork extends BaseWorker<Cabinet> {
    public CabinetWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        CabinetRestService.restGetCabinets(offset, limit, this);
    }
    @Override
    protected void doOnStart() {

    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<Cabinet> recs) {

    }
}
