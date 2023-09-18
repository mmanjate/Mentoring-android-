package mz.org.csaude.mentoring.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityLoginBinding;
import mz.org.csaude.mentoring.viewmodel.login.LoginVM;
import mz.org.csaude.mentoring.viewmodel.mentorship.MentorshipVM;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(LoginVM.class);
    }

    @Override
    public LoginVM getRelatedViewModel() {
        return (LoginVM) super.getRelatedViewModel();
    }
}