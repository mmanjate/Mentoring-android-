package mz.org.csaude.mentoring.service.metadata;

import android.app.ProgressDialog;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.user.User;

import java.sql.SQLException;

public interface LoadMetadataService {

    void load(final BaseActivity activity, final ProgressDialog progressDialog, User currentUser);

}
