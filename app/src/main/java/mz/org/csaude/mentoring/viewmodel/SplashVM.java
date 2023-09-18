package mz.org.csaude.mentoring.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataService;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;

public class SplashVM extends BaseViewModel implements RestResponseListener {

    private LoadMetadataService loadMetadataService;

    public SplashVM(@NonNull Application application) {
        super(application);
        loadMetadataService = new LoadMetadataServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    public void initAppConfiguration() {

        // Load metadata
        loadMetadataService.load();
    }


}
