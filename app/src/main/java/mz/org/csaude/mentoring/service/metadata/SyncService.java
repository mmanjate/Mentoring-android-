package mz.org.csaude.mentoring.service.metadata;

import mz.org.csaude.mentoring.base.activity.BaseActivity;

/**
 * Created by ialuj
 */
public interface SyncService {

    void execute();

    void setActivity(final BaseActivity activity);

}
