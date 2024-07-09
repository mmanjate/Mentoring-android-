package mz.org.csaude.mentoring.view.session;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.session.SessionAdapter;
import mz.org.csaude.mentoring.adapter.resource.ResourceAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySessionEaresourceBinding;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.viewmodel.session.SessionResourcesVM;

public class SessionEAResourceActivity extends BaseActivity {

    private ResourceAdapter resourceAdapter;

    ActivitySessionEaresourceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_session_earesource);
        binding.setViewModel(getRelatedViewModel());

        Intent intent = this.getIntent();
        getRelatedViewModel().setSession((Session) intent.getExtras().get("session"));

        setSupportActionBar(binding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Fecho da Sessão");

    }

    @Override
    public void displaySearchResults() {
        super.displaySearchResults();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rcvResources.setLayoutManager(mLayoutManager);
        binding.rcvResources.setItemAnimator(new DefaultItemAnimator());
        binding.rcvResources.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));

        resourceAdapter = new ResourceAdapter(binding.rcvResources, getRelatedViewModel().getNodeList(), this);
        binding.rcvResources.setAdapter(resourceAdapter);
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SessionResourcesVM.class);
    }

    @Override
    public SessionResourcesVM getRelatedViewModel() {
        return (SessionResourcesVM) super.getRelatedViewModel();
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

    public void onLongItemClick(View v, int position) {
        getRelatedViewModel().selectResource(position);
        resourceAdapter.notifyDataSetChanged();
    }



}