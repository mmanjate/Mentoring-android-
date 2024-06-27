package mz.org.csaude.mentoring.adapter.recyclerview.session;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.databinding.SessionListItemBinding;
import mz.org.csaude.mentoring.databinding.SessionSummaryItemBinding;
import mz.org.csaude.mentoring.model.session.SessionSummary;

public class SessionSummaryAdapter extends AbstractRecycleViewAdapter<SessionSummary> {
    public SessionSummaryAdapter(RecyclerView recyclerView, List<SessionSummary> records, BaseActivity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SessionSummaryItemBinding sessionListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.session_summary_item, parent, false);
        return new SessionSummaryViewHolder(sessionListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SessionSummary sessionSummary = records.get(position);

        SessionSummaryViewHolder sessionViewHolder = (SessionSummaryViewHolder) holder;
        sessionViewHolder.sessionSummaryItemBinding.setSessionSummary(sessionSummary);
    }

    public static class SessionSummaryViewHolder extends RecyclerView.ViewHolder {

        private SessionSummaryItemBinding sessionSummaryItemBinding;

        public SessionSummaryViewHolder(@NonNull SessionSummaryItemBinding sessionSummaryListItemBinding) {
            super(sessionSummaryListItemBinding.getRoot());
            this.sessionSummaryItemBinding = sessionSummaryListItemBinding;
        }
    }
}
