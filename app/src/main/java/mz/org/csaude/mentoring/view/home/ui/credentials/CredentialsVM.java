package mz.org.csaude.mentoring.view.home.ui.credentials;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.user.UserService;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.user.UserVM;

public class CredentialsVM extends UserVM {

    private UserService userService;
    protected String userPassWordCorrente;

    protected String userNovaPassWord;

    private boolean initialDataVisible;

    public CredentialsVM(@NonNull Application application) {
        super(application);
        this.userService = getApplication().getUserService();

    }
    @Override
    public void preInit() {
        this.user = getCurrentUser();
    }

    @Bindable
    public String getUserPassWordCorrente() {
        return userPassWordCorrente;
    }

    public void setUserPassWordCorrente(String userPassWordCorrente) {
        this.userPassWordCorrente = userPassWordCorrente;
        notifyPropertyChanged(BR.userPassWordCorrente);
    }

    @Bindable
    public String getUserNovaPassWord() {
        return userNovaPassWord;
    }

    public void setUserNovaPassWord(String userNovaPassWord) {
        this.userNovaPassWord = userNovaPassWord;
        notifyPropertyChanged(BR.userNovaPassWord);
    }


    public boolean isInitialDataVisible() {
        return initialDataVisible;
    }

    public void setInitialDataVisible(boolean initialDataVisible) {
        this.initialDataVisible = initialDataVisible;
    }

    @Override
    public void save() {
        if (Utilities.MD5Crypt(userPassWordCorrente).equals(getPassword())){
            if (!userNovaPassWord.equals(getUserPassRepeat())){
                Utilities.displayAlertDialog(getRelatedFragment().getContext(), "As senhas indicadas não conferem, por favor verificar.").show();
            }else {
                getRelatedRecord().setPassword(userNovaPassWord);
                super.save();
            }
        }else Utilities.displayAlertDialog(getRelatedFragment().getContext(), "A senha Corrente indicada é inválida.").show();
    }

}