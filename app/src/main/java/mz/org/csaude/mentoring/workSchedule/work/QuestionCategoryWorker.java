package mz.org.csaude.mentoring.workSchedule.work;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.worker.BaseWorker;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;
import mz.org.csaude.mentoring.workSchedule.rest.QuestionsCategoryRestService;

public class QuestionCategoryWorker extends BaseWorker<QuestionsCategory> {
    private QuestionsCategoryRestService questionsCategoryRestService;
    public QuestionCategoryWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.questionsCategoryRestService = new QuestionsCategoryRestService((Application) getApplicationContext());
    }
    @Override
    public void doOnlineSearch(long offset, long limit) throws SQLException {
        this.questionsCategoryRestService.restGetQuestionCategories(this);
    }

    @Override
    protected void doAfterSearch(String flag, List<QuestionsCategory> recs) throws SQLException {
        changeStatusToFinished();
        doOnFinish();
    }

    @Override
    protected void doOnStart() {

    }

    @Override
    protected void doOnFinish() {

    }

    @Override
    protected void doSave(List<QuestionsCategory> recs) {

    }
}
