package mz.org.csaude.mentoring.adapter.resource;

import android.app.Activity;
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
import mz.org.csaude.mentoring.databinding.ResourceListemItemBinding;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.resourceea.Resource;
import mz.org.csaude.mentoring.view.mentorship.CreateMentorshipActivity;
import mz.org.csaude.mentoring.view.resource.ResourceActivity;
import mz.org.csaude.mentoring.view.session.SessionActivity;
import mz.org.csaude.mentoring.view.session.SessionEAResourceActivity;
import mz.org.csaude.mentoring.viewmodel.resource.ResourceVM;

public class ResourceAdapter extends AbstractRecycleViewAdapter<Node> {


    public ResourceAdapter(RecyclerView recyclerView, List<Node> records, BaseActivity activity) {
        super(recyclerView, records, activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         ResourceListemItemBinding resourceListemItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.resource_listem_item, parent, false);
        return new ResourceViewHolder(resourceListemItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Node node = records.get(position);
        ((ResourceViewHolder) holder).resourceListemItemBinding.setNode(node);
        //((ResourceViewHolder) holder).resourceListemItemBinding.setResourceVM((ResourceVM) this.activity.getRelatedViewModel());
        if (activity instanceof SessionEAResourceActivity) {
            ((ResourceViewHolder) holder).resourceListemItemBinding.btnDownload.setImageResource(R.drawable.ic_done);
        } else {
            node.setItemSelected(true);
            ((ResourceViewHolder) holder).resourceListemItemBinding.btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ResourceActivity)activity).downloadResource(node);
                }
            });
        }
    }

    public class ResourceViewHolder extends RecyclerView.ViewHolder{

        ResourceListemItemBinding resourceListemItemBinding;
        public ResourceViewHolder(@NonNull ResourceListemItemBinding resourceListemItemBinding) {
            super(resourceListemItemBinding.getRoot());
            this.resourceListemItemBinding = resourceListemItemBinding;

            resourceListemItemBinding.getRoot().setOnClickListener(v -> {
                if (activity != null) {
                    if (activity instanceof SessionEAResourceActivity) {
                        ((SessionEAResourceActivity) activity).onLongItemClick(v, getAdapterPosition());
                    }
                }
                notifyItemChanged(getAdapterPosition());
                selectedPosition = getAdapterPosition();
                notifyItemChanged(selectedPosition);
            });
        }
    }
}
