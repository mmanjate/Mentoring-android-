package mz.org.csaude.mentoring.adapter.recyclerview.mentorship;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public class ZeroMentorshipAdapter extends AbstractRecycleViewAdapter<Mentorship> {
    public ZeroMentorshipAdapter(RecyclerView recyclerView, List<Mentorship> records, BaseActivity activity) {
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
