package mz.org.csaude.mentoring.viewmodel.formQuestion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.service.formQuestion.FormQuestionService;
import mz.org.csaude.mentoring.service.formQuestion.FormQuestionServiceImpl;

public class FormQuestionVM extends BaseViewModel {

    private FormQuestion formQuestion;

    private FormQuestionService formQuestionService;

    public FormQuestionVM(@NonNull Application application) {
        super(application);
        this.formQuestionService = new FormQuestionServiceImpl(application);
    }

    @Override
    public void preInit() {

    }

    @Bindable
    public Form getForm() {
        return this.formQuestion.getForm();
    }

    public void setForm(Form form) {
        this.formQuestion.setForm(form);
        notifyPropertyChanged(BR.form);
    }

    @Bindable
    public Question getQuestion() {
        return this.formQuestion.getQuestion();
    }

    public void setQuestion(Question question) {
        this.formQuestion.setQuestion(question);
        notifyPropertyChanged(BR.question);
    }

    @Bindable
    public boolean getMandatory() {
        return this.formQuestion.isMandatory();
    }

    public void setQuestion(boolean mandatory) {
        this.formQuestion.setMandatory(mandatory);
        notifyPropertyChanged(BR.mandatory);
    }

    @Bindable
    public Integer getSequence() {
        return this.formQuestion.getSequence();
    }

    public void setSequence(Integer sequence) {
        this.formQuestion.setSequence(sequence);
        notifyPropertyChanged(BR.sequence);
    }
    private Boolean applicable;

    @Bindable
    public Boolean getApplicable() {
        return this.formQuestion.getApplicable();
    }

    public void setApplicable(Boolean applicable) {
        this.formQuestion.setApplicable(applicable);
        notifyPropertyChanged(BR.applicable);
    }

    public void save() {
        try {
            this.formQuestionService.save(this.formQuestion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
