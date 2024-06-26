package mz.org.csaude.mentoring.adapter.resource;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.databinding.ResourceListemItemBinding;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.viewmodel.resource.ResourceVM;

public class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private BaseActivity activity;

    private List<Node> nodes;

    public ResourceAdapter(RecyclerView recyclerView, Activity activity, List<Node> nodes) {
        this.activity = (BaseActivity) activity;
        this.nodes = nodes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         ResourceListemItemBinding resourceListemItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.resource_listem_item, parent, false);

        return new ResourceViewHolder(resourceListemItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ResourceViewHolder) holder).resourceListemItemBinding.setNode(nodes.get(position));
        ((ResourceViewHolder) holder).resourceListemItemBinding.setResourceVM((ResourceVM) this.activity.getRelatedViewModel());
    }
    @Override
    public int getItemCount() {
        return this.nodes.size();
    }

    public class ResourceViewHolder extends RecyclerView.ViewHolder{

        ResourceListemItemBinding resourceListemItemBinding;
        public ResourceViewHolder(@NonNull ResourceListemItemBinding resourceListemItemBinding) {
            super(resourceListemItemBinding.getRoot());
            this.resourceListemItemBinding = resourceListemItemBinding;
        }
    }
}
