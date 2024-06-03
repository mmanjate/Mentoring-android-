package mz.org.csaude.mentoring.adapter.recyclerview.question;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.databinding.QuestionListItemBinding;
import mz.org.csaude.mentoring.databinding.RondaListItemBinding;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.ronda.Ronda;

public class QuestionAdapter extends AbstractRecycleViewAdapter<FormQuestion> {

    public QuestionAdapter(RecyclerView recyclerView, List<FormQuestion> records, BaseActivity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionListItemBinding questionListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.question_list_item, parent, false);
        return new QuestionAdapter.FormQuestionViewHolder(questionListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FormQuestionViewHolder) holder).questionListItemBinding.setFormQuestion(super.records.get(position));
    }
    @Override
    public int getItemCount() {
        return records.size();
    }

    public class FormQuestionViewHolder extends RecyclerView.ViewHolder {

        private QuestionListItemBinding questionListItemBinding;

        public FormQuestionViewHolder(@NonNull QuestionListItemBinding questionListItemBinding) {
            super(questionListItemBinding.getRoot());
            this.questionListItemBinding = questionListItemBinding;
        }
    }
}
