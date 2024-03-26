package mz.org.csaude.mentoring.view.session;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySessionBinding;
import mz.org.csaude.mentoring.viewmodel.session.SessionListVM;
import mz.org.csaude.mentoring.viewmodel.session.SessionVM;

public class SessionActivity extends BaseActivity {

    private ActivitySessionBinding sessionBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionBinding = DataBindingUtil.setContentView(this, R.layout.activity_session);
        sessionBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SessionVM.class);
    }

    @Override
    public SessionVM getRelatedViewModel() {
        return (SessionVM) super.getRelatedViewModel();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
