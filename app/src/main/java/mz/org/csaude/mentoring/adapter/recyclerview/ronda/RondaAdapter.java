package mz.org.csaude.mentoring.adapter.recyclerview.ronda;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.model.ronda.Ronda;

public class RondaAdapter extends AbstractRecycleViewAdapter<Ronda> {

    public RondaAdapter(RecyclerView recyclerView, List<Ronda> records, Activity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
