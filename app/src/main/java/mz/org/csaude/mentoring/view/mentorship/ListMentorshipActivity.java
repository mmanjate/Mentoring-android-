package mz.org.csaude.mentoring.view.mentorship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.listmentorship.ListMentorshipAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityListMentorshipBinding;
import mz.org.csaude.mentoring.viewmodel.mentorship.ListMentorshipVM;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

import android.os.Bundle;

public class ListMentorshipActivity extends BaseActivity {

    private ActivityListMentorshipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMentorshipBinding.inflate(getLayoutInflater());

        ListMentorshipVM listMentorshipVM = this.getRelatedViewModel();

        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recyclerViewMentorship;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ListMentorshipAdapter listMentorshipAdapter = new ListMentorshipAdapter(listMentorshipVM.getMentorshipsByTutor(getCurrentUser().getUuid()));

        recyclerView.setAdapter(listMentorshipAdapter);
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(ListMentorshipVM.class);
    }

    @Override
    public ListMentorshipVM getRelatedViewModel() {
        return (ListMentorshipVM) super.getRelatedViewModel();
    }
}