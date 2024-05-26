package mz.org.csaude.mentoring.base.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PackageInfoCompat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.common.ApplicationStep;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.model.user.User;

/**
 * Generic class that represent all application activities
 */
public abstract class BaseActivity extends AppCompatActivity implements GenericActivity {

    /**
     * {@link BaseViewModel} Activity related viewModel
     */
    protected BaseViewModel relatedViewModel;

    /**
     * {@link PackageInfo} application info
     */
    private PackageInfo pinfo;

    private Tutored tutored;

    private Integer positionRemoved;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.relatedViewModel = initViewModel();

        Intent intent = this.getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                if (this.relatedViewModel != null) {
                    if (bundle.getSerializable("relatedRecord") != null) {
                        this.relatedViewModel.setSelectedRecord(bundle.getSerializable("relatedRecord"));
                    }
                }
            }
        }

        if (this.relatedViewModel != null) {
            this.relatedViewModel.setRelatedActivity(this);
            //this.relatedViewModel.initLoadingDialog();
        }

        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mz.org.csaude.mentoring.ACTION_LOGOUT");

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (this.relatedViewModel != null) {
            this.relatedViewModel.preInit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     *
     * @return application version number
     */
    public long getAppVersionNumber(){
        return PackageInfoCompat.getLongVersionCode(pinfo);
    }

    /**
     *
     * @return application version name
     */
    public String getAppVersionName(){
        return pinfo.versionName;
    }

    /**
     * Forward from current activity to a new one passed on the param without finishing current one
     * @param clazz target activity
     */
    public void nextActivity(Class clazz){
        nextActivity(clazz, null, false);
    }

    /**
     * Forward from current activity to a new one passed on the param finishing current one
     * @param clazz target activity
     */
    public void nextActivityFinishingCurrent(Class clazz){
        nextActivity(clazz, null, true);
    }

    /**
     * Forward from current activity to a new one passed on the param without finishing current one, sending params
     *
     * @param clazz target activity
     * @param params params to be sent to the other activity
     */
    public void nextActivity(Class clazz, Map<String, Object> params){
        nextActivity(clazz, params, false);
    }

    /**
     * Forward from current activity to a new one passed on the param finishing current one, sending params
     *
     * @param clazz target activity
     * @param params params to be sent to the other activity
     */
    public void nextActivityFinishingCurrent(Class clazz, Map<String, Object> params){
        nextActivity(clazz, params, true);
    }

    /**
     * Move from one {@link android.app.Activity} to another
     *
     * @param clazz target activity
     * @param params params to be sent
     * @param finishCurrentActivity condition to finish or not the current activity
     */
    private void nextActivity(Class clazz, Map<String, Object> params, boolean finishCurrentActivity){

        Intent intent = new Intent(getApplication(), clazz);
        Bundle bundle = new Bundle();

        if (params != null && params.size() > 0){
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof Serializable) {
                    bundle.putSerializable(entry.getKey(), (Serializable) entry.getValue());
                }
            }
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (finishCurrentActivity) finish();
    }

    public <T extends BaseActivity> void nextActivityWithGenericParams(Class<T> clazz){
        Map<String, Object> params = new HashMap<>();
        //params.put("user", getCurrentUser());
        nextActivity(clazz,params);
    }

    public <T extends BaseActivity> void nextActivityFinishingCurrentWithGenericParams(Class<T> clazz){
        Map<String, Object> params = new HashMap<>();
        //params.put("user", getCurrentUser());
        nextActivity(clazz,params);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        /*if (this instanceof  LoginActivity){
            finishAffinity();
            System.exit(0);
        }else*/
            super.onBackPressed();
    }

    /**
     *
     * @return the related {@link BaseViewModel}
     */
    public BaseViewModel getRelatedViewModel() {
        return relatedViewModel;
    }

    /**
     *
     * @return the application current {@link User}
     */
    public User getCurrentUser() {
        return ((MentoringApplication) getApplication()).getAuthenticatedUser();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_INIT}
     */
    protected void changeApplicationStepToInit(){
        getApplicationStep().changeToInit();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_LIST}
     */
    protected void changeApplicationStepToList(){
        this.getApplicationStep().changeToList();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_DISPLAY}
     */
    protected void changeApplicationStepToDisplay(){
        getApplicationStep().changeToDisplay();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_EDIT}
     */
    protected void changeApplicationStepToEdit(){
        getApplicationStep().changeToEdit();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_SAVE}
     */
    protected void changeApplicationStepToSave(){
        getApplicationStep().changeToSave();
    }

    /**
     * Change the current {@link ApplicationStep} to {@link ApplicationStep#STEP_CREATE}
     */
    protected void changeApplicationStepToCreate(){
        getApplicationStep().changetocreate();
    }

    protected void changeApplicationStepToDownload(){
        getApplicationStep().changetoDownload();
    }

    /**
     *
     * @return the application current step
     */
    public ApplicationStep getApplicationStep() {
        return ((MentoringApplication) getApplication()).getApplicationStep();
    }

    public boolean isViewListEditButton() {
        return getRelatedViewModel().isViewListEditButton();
    }

    public void setViewListEditButton(boolean viewListEditButton) {
        this.relatedViewModel.setViewListEditButton(viewListEditButton);
    }

    public boolean isViewListRemoveButton() {
        return getRelatedViewModel().isViewListRemoveButton();
    }

    public void setViewListRemoveButton(boolean viewListRemoveButton) {
        this.relatedViewModel.setViewListRemoveButton(viewListRemoveButton);
    }

    public Integer getPositionRemoved() {
        return positionRemoved;
    }

    public void setPositionRemoved(Integer positionRemoved) {
        this.positionRemoved = positionRemoved;
    }

}
