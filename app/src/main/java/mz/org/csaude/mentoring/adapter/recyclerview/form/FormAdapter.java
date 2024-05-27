package mz.org.csaude.mentoring.adapter.recyclerview.form;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.databinding.FormListItemBinding;
import mz.org.csaude.mentoring.model.form.Form;

public class FormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Form> formList;

    public FormAdapter(List<Form> formList) {
        this.formList = formList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FormListItemBinding formListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.form_list_item, parent, false);
        return new FormViewHolder(formListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FormViewHolder) holder).formListItemBinding.setForm(formList.get(position));
    }

    @Override
    public int getItemCount() {
        return formList.size();
    }

    public class FormViewHolder extends RecyclerView.ViewHolder {

        private FormListItemBinding formListItemBinding;

        public FormViewHolder(@NonNull FormListItemBinding formListItemBinding) {
            super(formListItemBinding.getRoot());
            this.formListItemBinding = formListItemBinding;
        }
    }
}
