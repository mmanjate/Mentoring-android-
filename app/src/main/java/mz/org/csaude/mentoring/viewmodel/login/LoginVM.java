package mz.org.csaude.mentoring.viewmodel.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.auth.SessionManager;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.user.UserService;
import mz.org.csaude.mentoring.service.user.UserServiceImpl;
import mz.org.csaude.mentoring.service.user.UserSyncService;
import mz.org.csaude.mentoring.workSchedule.executor.WorkerScheduleExecutor;
import mz.org.csaude.mentoring.workSchedule.rest.UserRestService;

public class LoginVM extends BaseViewModel implements RestResponseListener<User> {

    private final UserService userService;
    private final User user;

    private boolean remeberMe;

    private boolean authenticating;

    private SessionManager sessionManager;

    private UserSyncService userSyncService;


    public LoginVM(@NonNull Application application) {
        super(application);
        userService = new UserServiceImpl(application);
        this.user= new User();
        this.userSyncService = new UserRestService(application, this.user);
    }

    @Override
    public void preInit() {
        this.sessionManager = new SessionManager(getRelatedActivity());
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
    public String getUserPassword() {
        return this.user.getPassword();
    }
    public void setUserPassword(String password) {
        this.user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public void doLogin() {
        userSyncService.doOnlineLogin(this);
        /*try {
            this.userService.login(this.user);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void doOnRestSucessResponse(User user) {
        userSyncService.getUserByCedencials(user);
        OneTimeWorkRequest request = WorkerScheduleExecutor.getInstance(getApplication()).runPotLoginSync(user);

        WorkerScheduleExecutor.getInstance(getApplication()).getWorkManager().getWorkInfoByIdLiveData(request.getId()).observe(getRelatedActivity(), workInfo -> {
            if (workInfo != null) {
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){
                    WorkInfo.State state = workInfo.getState();
                    goHome();
                }
            }
        });
    }

    private void goHome() {

    }

    @Bindable
    public boolean isAuthenticating() {
        return false;
    }

    public void setAuthenticating(boolean authenticating) {
        this.authenticating = authenticating;
        notifyChange();
    }

    @Bindable
    public boolean isRemeberMe() {
        return remeberMe;
    }

    public void setRemeberMe(boolean remeberMe) {
        this.remeberMe = remeberMe;
        notifyPropertyChanged(BR.remeberMe);
    }

    public void changeRemeberMeStatus() {
        setRemeberMe(!isRemeberMe());
    }

}
