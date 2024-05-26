package mz.org.csaude.mentoring.view.ronda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.ListbleRecycleViewAdapter;
import mz.org.csaude.mentoring.adapter.recyclerview.ronda.RondaAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityMentoringCycleListBinding;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.util.RondaType;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaSearchVM;

public class RondaActivity extends BaseActivity {
    private ActivityMentoringCycleListBinding mentoringCycleListBinding;
    private RecyclerView rondasRecyclerView;
    private ListbleRecycleViewAdapter listbleRecycleViewAdapter;
    private RondaAdapter rondaAdapter;
    List<Listble> rondas;
    private String title;
    private RondaType rondaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mentoringCycleListBinding = DataBindingUtil.setContentView(this, R.layout.activity_mentoring_cycle_list);
        mentoringCycleListBinding.setViewModel(getRelatedViewModel());
        rondasRecyclerView = mentoringCycleListBinding.rcvRondas;
        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            Ronda ronda = (Ronda) intent.getExtras().get("createdRonda");
            title = (String) intent.getExtras().get("title");
            rondaType = (RondaType) intent.getExtras().get("rondaType");
            bundle.putSerializable("createdRonda", ronda);
            bundle.putSerializable("title", title);
            bundle.putSerializable("rondaType", rondaType);
            intent.putExtras(bundle);
            setUpToolbar(title);
            initAdapter();
        }
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(RondaSearchVM.class);
    }
    @Override
    public RondaSearchVM getRelatedViewModel() {
        return (RondaSearchVM) super.getRelatedViewModel();
    }
    public void initAdapter() {
        this.rondas = getRelatedViewModel().getAllRondas(rondaType);
        if (Utilities.listHasElements(this.rondas)) {
            populateRecyclerView();
        }
    }

    private void setUpToolbar(String title) {
        setSupportActionBar(mentoringCycleListBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        getRelatedViewModel().setViewListEditButton(false);
        getRelatedViewModel().setViewListRemoveButton(false);
    }

    private void populateRecyclerView(){
        if (rondaAdapter != null) {
            rondaAdapter.notifyDataSetChanged();
        }else {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rondasRecyclerView.setLayoutManager(mLayoutManager);
            rondasRecyclerView.setItemAnimator(new DefaultItemAnimator());
            rondasRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));

            rondaAdapter = new RondaAdapter(rondasRecyclerView, getRelatedViewModel().getRondaList(), this);
            rondasRecyclerView.setAdapter(rondaAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button click
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}