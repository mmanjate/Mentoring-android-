package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.workSchedule.rest.CabinetRestService;

public class CabinetWorker extends BaseWorker<Cabinet> {

    private CabinetRestService cabinetRestService;

    public CabinetWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.cabinetRestService = new CabinetRestService((Application) getApplicationContext());
    }

    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        this.cabinetRestService.restGetCabinets(offset, limit, this);
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
