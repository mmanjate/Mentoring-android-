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
import mz.org.csaude.mentoring.model.rondatype.RondaType;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaSearchVM;

public class RondaActivity extends BaseActivity {
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
        Intent intent = this.getIntent();
        if(intent!=null && intent.getExtras()!=null) {
            getRelatedViewModel().setTitle((String) intent.getExtras().get("title"));
            getRelatedViewModel().setRondaType((RondaType) intent.getExtras().get("rondaType"));
            setUpToolbar(getRelatedViewModel().getTitle());
            //getRelatedViewModel().initSearch();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRelatedViewModel().initSearch();
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(RondaSearchVM.class);
    }
    @Override
    public RondaSearchVM getRelatedViewModel() {
        return (RondaSearchVM) super.getRelatedViewModel();
    }

    private void setUpToolbar(String title) {
        setSupportActionBar(mentoringCycleListBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        getRelatedViewModel().setViewListEditButton(false);
        getRelatedViewModel().setViewListRemoveButton(false);
    }

    public void populateRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rondasRecyclerView.setLayoutManager(mLayoutManager);
        rondasRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rondasRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));

        rondaAdapter = new RondaAdapter(rondasRecyclerView, getRelatedViewModel().getSearchResults(), this);
        rondasRecyclerView.setAdapter(rondaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}