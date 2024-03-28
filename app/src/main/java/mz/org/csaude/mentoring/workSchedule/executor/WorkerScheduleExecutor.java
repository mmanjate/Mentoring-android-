package mz.org.csaude.mentoring.workSchedule.executor;

import android.app.Application;

import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mz.org.csaude.mentoring.base.application.MentoringApplication;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.workSchedule.work.CabinetWorker;
import mz.org.csaude.mentoring.workSchedule.work.CareerWorker;
import mz.org.csaude.mentoring.workSchedule.work.DistrictWorker;
import mz.org.csaude.mentoring.workSchedule.work.PartnerWorker;
import mz.org.csaude.mentoring.workSchedule.work.ProfessionalCategoryWorker;
import mz.org.csaude.mentoring.workSchedule.work.ProvinceWorker;
import mz.org.csaude.mentoring.workSchedule.work.TutorWorker;
import mz.org.csaude.mentoring.workSchedule.work.UserWorker;

public class WorkerScheduleExecutor {

    private static final String TAG = "WorkerScheduler";
    public static final int JOB_ID = 1000;
    public static final int ONE_TIME_REQUEST_JOB_ID = 1001;

    private WorkManager workManager;

    private static WorkerScheduleExecutor instance;

    private MentoringApplication application;

    private List<Setting> settings;

    private WorkerScheduleExecutor(Application application) {
        this.application = (MentoringApplication) application;
        this.workManager = WorkManager.getInstance(application);
    }

    public WorkManager getWorkManager() {
        return workManager;
    }

    public static WorkerScheduleExecutor getInstance(Application application) {
        if (instance == null) {
            instance = new WorkerScheduleExecutor(application);
        }
        return instance;
    }
    public OneTimeWorkRequest runinitialSync() {
        OneTimeWorkRequest provinceOneTimeWorkRequest = new OneTimeWorkRequest.Builder(ProvinceWorker.class).addTag("ONE_TIME_CABINET_ID" + ONE_TIME_REQUEST_JOB_ID).build();
        OneTimeWorkRequest districtOneTimeWorkRequest = new OneTimeWorkRequest.Builder(DistrictWorker.class).addTag("ONE_TIME_CAREER_ID" + ONE_TIME_REQUEST_JOB_ID).build();
        OneTimeWorkRequest categoriesOneTimeWorkRequest = new OneTimeWorkRequest.Builder(ProfessionalCategoryWorker.class).addTag("ONE_TIME_CATEGORIES_ID" + ONE_TIME_REQUEST_JOB_ID).build();
        OneTimeWorkRequest partnersOneTimeWorkRequest = new OneTimeWorkRequest.Builder(PartnerWorker.class).addTag("ONE_TIME_PARTNERS_ID" + ONE_TIME_REQUEST_JOB_ID).build();

        workManager.beginUniqueWork("INITIAL_APP_SETUP", ExistingWorkPolicy.KEEP, provinceOneTimeWorkRequest)
                    .then(Arrays.asList(districtOneTimeWorkRequest, partnersOneTimeWorkRequest))
                    .then(categoriesOneTimeWorkRequest).enqueue();
        return categoriesOneTimeWorkRequest;

    }

    public OneTimeWorkRequest runPostLoginSync(User user) {
        /*Data inputData = new Data.Builder()
                .putString("username", user.getUserName())
                .putString("password", user.getPassword())
                .build();
        OneTimeWorkRequest userOneTimeWorkRequest = new OneTimeWorkRequest.Builder(UserWorker.class).addTag("ONE_TIME_USER_ID" + ONE_TIME_REQUEST_JOB_ID).setInputData(inputData).build();*/
        OneTimeWorkRequest tutorOneTimeWorkRequest = new OneTimeWorkRequest.Builder(TutorWorker.class).addTag("ONE_TIME_TUTOR_ID" + ONE_TIME_REQUEST_JOB_ID).build();

        workManager.enqueue( tutorOneTimeWorkRequest);
        return tutorOneTimeWorkRequest;
    }
}
