package mz.org.csaude.mentoring.adapter.recyclerview.listmentorship;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.databinding.ListMentorshipBinding;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;

public class ListMentorshipAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Mentorship> mentorships;

    public ListMentorshipAdapter(List<Mentorship> mentorships) {
        this.mentorships = mentorships;
    }

    @NonNull
    @Override
    public MentorShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListMentorshipBinding listItem;

        listItem = ListMentorshipBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MentorShipViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MentorShipViewHolder) holder).binding.mentorshipFormName.setText(mentorships.get(position).getForm().getName());
        ((MentorShipViewHolder) holder).binding.sessionStatus.setText(mentorships.get(position).getSession().getStatus().equals("COMPLETE") ? "complete" : "incomplete");
        ((MentorShipViewHolder) holder).binding.tutoredName.setText(mentorships.get(position).getTutored().getEmployee().getName());
        ((MentorShipViewHolder) holder).binding.healthFacility.setText(mentorships.get(position).getSession().getRonda().getHealthFacility().getDescription());
        ((MentorShipViewHolder) holder).binding.processDate.setText("(" + mentorships.get(position).getSession().getStartDate().toString() + ")");

    }

    @Override
    public int getItemCount() {
        return mentorships.size();
    }

    public static class MentorShipViewHolder extends RecyclerView.ViewHolder{

        private ListMentorshipBinding binding;
        public MentorShipViewHolder(ListMentorshipBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
