package mz.org.csaude.mentoring.adapter.recyclerview.tutored;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.generic.AbstractRecycleViewAdapter;
import mz.org.csaude.mentoring.databinding.TutoredListItemBinding;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class TutoredAdapter extends AbstractRecycleViewAdapter<Tutored> {

    List<Tutored> tutoredList;

    public TutoredAdapter(RecyclerView recyclerView, List<Tutored> records, Activity activity) {
        super(recyclerView, records, activity);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TutoredListItemBinding tutoredListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.tutored_list_item, parent, false);
        return new TutoredViewHolder(tutoredListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TutoredViewHolder) holder).tutoredListItemBinding.setTutored(tutoredList.get(position));
    }

    @Override
    public int getItemCount() {
        return tutoredList.size();
    }

    public class TutoredViewHolder extends RecyclerView.ViewHolder {

        private TutoredListItemBinding tutoredListItemBinding;

        public TutoredViewHolder(@NonNull TutoredListItemBinding tutoredListItemBinding) {
            super(tutoredListItemBinding.getRoot());
            this.tutoredListItemBinding = tutoredListItemBinding;
        }
    }
}
