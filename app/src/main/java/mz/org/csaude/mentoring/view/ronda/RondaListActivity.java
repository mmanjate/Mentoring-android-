package mz.org.csaude.mentoring.view.ronda;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.ronda.RondaAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentoringCycleListBinding;
import mz.org.csaude.mentoring.listner.recyclerView.IOnLoadMoreListener;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaSearchVM;

public class RondaListActivity extends BaseActivity {

    private ActivityMentoringCycleListBinding mentoringCycleListBinding;
    private RecyclerView rondasRecyclerView;

    private RondaAdapter rondaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mentoringCycleListBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentoring_cycle_list);
        mentoringCycleListBinding.setViewModel(getRelatedViewModel());
        rondasRecyclerView = mentoringCycleListBinding.rcvRondas;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(RondaSearchVM.class);
    }

    @Override
    public RondaSearchVM getRelatedViewModel() {
        return (RondaSearchVM) super.getRelatedViewModel();
    }

    public void displaySearchResult() {
        if (rondaAdapter == null) {
            rondaAdapter = new RondaAdapter(rondasRecyclerView, getRelatedViewModel().getAllDisplyedRecords(), this);
            rondasRecyclerView.setAdapter(rondaAdapter);
        }

        if (rondaAdapter.getOnLoadMoreListener() == null) {
            rondaAdapter.setOnLoadMoreListener(() -> getRelatedViewModel().loadMoreRecords(rondasRecyclerView, rondaAdapter));
        }
    }
}