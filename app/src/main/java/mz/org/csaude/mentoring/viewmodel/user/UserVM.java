package mz.org.csaude.mentoring.viewmodel.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.user.UserService;

public class UserVM extends BaseViewModel {

    private UserService userService;

    private User user;

    public UserVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public String getUserName() {
        return this.user.getUserName();
    }

    public void setUserName(String userName) {
        this.user.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassword() {
        return this.user.getPassword();
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public void save() {
        try {
            this.userService.save(this.user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
