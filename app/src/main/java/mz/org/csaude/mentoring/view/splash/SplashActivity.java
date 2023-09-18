package mz.org.csaude.mentoring.view.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySplashBinding;
import mz.org.csaude.mentoring.viewmodel.SplashVM;
import mz.org.csaude.mentoring.viewmodel.login.LoginVM;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding splashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashBinding.setViewModel(getRelatedViewModel());

        getRelatedViewModel().initAppConfiguration();

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SplashVM.class);
    }

    @Override
    public SplashVM getRelatedViewModel() {
        return (SplashVM) super.getRelatedViewModel();
    }
}