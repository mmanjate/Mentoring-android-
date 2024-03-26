package mz.org.csaude.mentoring.adapter.tutored;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.databinding.TutoredListItemBinding;
import mz.org.csaude.mentoring.model.tutored.Tutored;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Jose Julai Ritsure
 */
public class TutoredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<Tutored> tutoreds;

    public TutoredAdapter(RecyclerView recyclerView, List<Tutored> records, Activity activity) {
        this.activity = activity;
        this.tutoreds = records;
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        TutoredListItemBinding tutoredListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.tutored_list_item, parent, false);
        return new TutoredViewHolder(tutoredListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((TutoredViewHolder) viewHolder).tutoredListItemBinding.setTutored(tutoreds.get(position));
    }

    @Override
    public int getItemCount() {
        return tutoreds.size();
    }

    public class TutoredViewHolder extends RecyclerView.ViewHolder {

        private TutoredListItemBinding tutoredListItemBinding;

        public TutoredViewHolder(@NonNull TutoredListItemBinding tutoredListItemBinding) {
            super(tutoredListItemBinding.getRoot());
            this.tutoredListItemBinding = tutoredListItemBinding;
        }
    }
}
