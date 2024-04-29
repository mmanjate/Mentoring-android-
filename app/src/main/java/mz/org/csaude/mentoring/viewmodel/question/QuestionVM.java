package mz.org.csaude.mentoring.viewmodel.question;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.question.QuestionType;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;
import mz.org.csaude.mentoring.service.question.QuestionService;
import mz.org.csaude.mentoring.service.question.QuestionServiceImpl;

public class QuestionVM extends BaseViewModel {

    private Question question;

    private QuestionService questionService;

    public QuestionVM(@NonNull Application application) {
        super(application);
        this.questionService = new QuestionServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    private String code;


    @Bindable
    public String getCode() {
        return this.question.getCode();
    }

    public void setCode(String code) {
        this.question.setCode(code);
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public String getQuestion() {
        return this.question.getQuestion();
    }

    public void setQuestion(String question) {
        this.question.setQuestion(question);
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public QuestionsCategory getQuestionsCategory() {
        return this.question.getQuestionsCategory();
    }

    public void setQuestionType(QuestionsCategory questionsCategory) {
        this.question.setQuestionsCategory(questionsCategory);
        notifyPropertyChanged(BR.questionsCategory);
    }
    public void save() {
        try {
            this.questionService.save(this.question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
