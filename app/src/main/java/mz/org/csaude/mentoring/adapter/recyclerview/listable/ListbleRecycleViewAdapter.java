package mz.org.csaude.mentoring.adapter.recyclerview.listable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.databinding.HeaderitemBinding;
import mz.org.csaude.mentoring.databinding.ListableItemBinding;
import mz.org.csaude.mentoring.listner.dialog.IListbleDialogListener;
import mz.org.csaude.mentoring.util.Utilities;


public class ListbleRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IListbleDialogListener {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private BaseActivity activity;
    private List<Listble> listbles;
    private RecyclerView recyclerView;

    public ListbleRecycleViewAdapter(RecyclerView recyclerView, List<Listble> listbles, BaseActivity activity) {
        this.activity = activity;
        this.listbles = listbles;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            ListableItemBinding listableItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.listable_item, parent, false);
            return new ListbleViewHolder(listableItemBinding);
        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            HeaderitemBinding headerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.headeritem, parent, false);
            return new HeaderViewHolder(headerItemBinding);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            if (listbles.size() != 0) {
                headerViewHolder.headeritemBinding.setListble(listbles.get(viewHolder.getAdapterPosition()));
            }
            headerViewHolder.headeritemBinding.setViewListEditButton(activity.isViewListEditButton());
            headerViewHolder.headeritemBinding.setViewListRemoveButton(activity.isViewListRemoveButton());
        } else if (viewHolder instanceof ListbleViewHolder) {
            ((ListbleViewHolder) viewHolder).listableItemBinding.setViewModel(activity.getRelatedViewModel());
            ((ListbleViewHolder) viewHolder).listableItemBinding.setListble(listbles.get(viewHolder.getAdapterPosition() - 1));
            ((ListbleViewHolder) viewHolder).listableItemBinding.setViewListEditButton(activity.isViewListEditButton());
            ((ListbleViewHolder) viewHolder).listableItemBinding.setViewListRemoveButton(activity.isViewListRemoveButton());

            ((ListbleViewHolder) viewHolder).listableItemBinding.edtNotes.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    /*if (s != null && Utilities.stringHasValue(s.toString())){
                        getItemAtPosition(viewHolder.getAdapterPosition()).setNotes(s.toString());
                    }*/
                }
            });

            ((ListbleViewHolder) viewHolder).listableItemBinding.edtQtyDestroy.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                   if (s != null && Utilities.stringHasValue(s.toString()) && Utilities.isNumeric(s.toString())){

                       if (getItemAtPosition(viewHolder.getAdapterPosition()) != null) {
                          // getItemAtPosition(viewHolder.getAdapterPosition()).setQtyToModify(Integer.valueOf(s.toString()));
                       }
                   } else {
                       //getItemAtPosition(viewHolder.getAdapterPosition()).setQtyToModify(0);
                   }
                }
            });

            ((ListbleViewHolder) viewHolder).listableItemBinding.edtQtyReturned.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.length()>0){
                        if (getItemAtPosition(viewHolder.getAdapterPosition()) != null) {
                           // getItemAtPosition(viewHolder.getAdapterPosition()).setQtyToModify(Integer.valueOf((s).toString()));
                        }
                    }

                }
            });

        }
    }

    public List<Listble> getData(){
        return this.listbles;
    }

    public Listble getItemAtPosition(int position){
        if (!Utilities.listHasElements(listbles)) return null;

        try {
            return listbles.get(position-1);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public void remove(int position) {
        Listble listble = listbles.get(position);

        listbles.remove(listbles.get(position));

        activity.getRelatedViewModel().setSelectedListble(listble);

        notifyItemRemoved(position);
        recyclerView.removeViewAt(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return listbles.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    public List<Listble> getListbles() {
        return listbles;
    }

    @Override
    public void remove(BaseModel baseModel) { }

    public class ListbleViewHolder extends RecyclerView.ViewHolder {

        private ListableItemBinding listableItemBinding;

        public ListbleViewHolder(@NonNull ListableItemBinding listableItemBinding) {
            super(listableItemBinding.getRoot());
            this.listableItemBinding = listableItemBinding;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        private HeaderitemBinding headeritemBinding;

        public HeaderViewHolder(@NonNull HeaderitemBinding headeritemBinding) {
            super(headeritemBinding.getRoot());
            this.headeritemBinding = headeritemBinding;
        }
    }
}