package mz.org.csaude.mentoring.adapter.recyclerview.tutored;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.databinding.TutoredListItemBinding;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class TutoredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Tutored> tutoredList;

    public TutoredAdapter(List<Tutored> tutoredList) {
        this.tutoredList = tutoredList;
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
