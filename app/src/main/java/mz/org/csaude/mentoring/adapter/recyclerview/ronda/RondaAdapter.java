package mz.org.csaude.mentoring.adapter.recyclerview.ronda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.databinding.RondaListItemBinding;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaSearchVM;

public class RondaAdapter extends AbstractRecycleViewAdapter<Ronda> {

    public RondaAdapter(RecyclerView recyclerView, List<Ronda> records, BaseActivity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RondaListItemBinding rondaListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.ronda_list_item, parent, false);
        return new RondaAdapter.RondaViewHolder(rondaListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Ronda ronda = super.records.get(position);
        ((RondaAdapter.RondaViewHolder) holder).rondaListItemBinding.setRonda(ronda);

        ((RondaViewHolder) holder).rondaListItemBinding.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RondaSearchVM) activity.getRelatedViewModel()).goToMentoriships(ronda);
            }
        });

        ((RondaViewHolder) holder).rondaListItemBinding.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RondaSearchVM) activity.getRelatedViewModel()).printRondaSummary(ronda);
            }
        });

        ((RondaViewHolder) holder).rondaListItemBinding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RondaSearchVM) activity.getRelatedViewModel()).edit(ronda);
            }
        });

        ((RondaViewHolder) holder).rondaListItemBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RondaSearchVM) activity.getRelatedViewModel()).delete(ronda);
            }
        });
    }

    public class RondaViewHolder extends RecyclerView.ViewHolder {

        private RondaListItemBinding rondaListItemBinding;

        public RondaViewHolder(@NonNull RondaListItemBinding rondaListItemBinding) {
            super(rondaListItemBinding.getRoot());
            this.rondaListItemBinding = rondaListItemBinding;
        }
    }
}
