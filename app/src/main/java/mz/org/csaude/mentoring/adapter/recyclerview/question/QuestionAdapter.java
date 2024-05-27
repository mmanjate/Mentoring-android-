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
import mz.org.csaude.mentoring.databinding.RondaListItemBinding;
import mz.org.csaude.mentoring.model.ronda.Ronda;

public class QuestionAdapter extends AbstractRecycleViewAdapter<Ronda> {

    public QuestionAdapter(RecyclerView recyclerView, List<Ronda> records, Activity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RondaListItemBinding rondaListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.ronda_list_item, parent, false);
        return new QuestionAdapter.RondaViewHolder(rondaListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((QuestionAdapter.RondaViewHolder) holder).rondaListItemBinding.setRonda(super.records.get(position));
    }
    @Override
    public int getItemCount() {
        return records.size();
    }

    public class RondaViewHolder extends RecyclerView.ViewHolder {

        private RondaListItemBinding rondaListItemBinding;

        public RondaViewHolder(@NonNull RondaListItemBinding rondaListItemBinding) {
            super(rondaListItemBinding.getRoot());
            this.rondaListItemBinding = rondaListItemBinding;
        }
    }
}
