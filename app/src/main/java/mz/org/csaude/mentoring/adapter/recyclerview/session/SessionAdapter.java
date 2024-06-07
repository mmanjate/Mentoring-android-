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
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.viewmodel.session.SessionListVM;

public class SessionAdapter extends AbstractRecycleViewAdapter<Session> {
    public SessionAdapter(RecyclerView recyclerView, List<Session> records, BaseActivity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SessionListItemBinding sessionListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.session_list_item, parent, false);
        return new SessionViewHolder(sessionListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Session session = records.get(position);
        SessionViewHolder sessionViewHolder = (SessionViewHolder) holder;
        sessionViewHolder.sessionListItemBinding.setSession(session);
        sessionViewHolder.sessionListItemBinding.setViewModel((SessionListVM) activity.getRelatedViewModel());
    }

     public class SessionViewHolder extends RecyclerView.ViewHolder {

        private final SessionListItemBinding sessionListItemBinding;

        public SessionViewHolder(@NonNull SessionListItemBinding sessionListItemBinding) {
            super(sessionListItemBinding.getRoot());
            this.sessionListItemBinding = sessionListItemBinding;
        }
    }
}
