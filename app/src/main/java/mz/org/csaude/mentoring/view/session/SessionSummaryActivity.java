package mz.org.csaude.mentoring.view.session;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.recyclerview.session.SessionAdapter;
import mz.org.csaude.mentoring.adapter.recyclerview.session.SessionSummaryAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySessionSummaryBinding;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.viewmodel.session.SessionSummaryVM;

public class SessionSummaryActivity extends BaseActivity {

    private SessionSummaryAdapter sessionSummaryAdapter;
    private ActivitySessionSummaryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_session_summary);
        binding.setViewModel(getRelatedViewModel());

        Intent intent = this.getIntent();
        getRelatedViewModel().setSession((Session) intent.getExtras().get("session"));

        setSupportActionBar(binding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Fecho da Sessão");

        getRelatedViewModel().generateSessionSummary();

        Intent finishIntent = new Intent("FINISH_ACTIVITY");
        LocalBroadcastManager.getInstance(this).sendBroadcast(finishIntent);
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SessionSummaryVM.class);
    }

    @Override
    public SessionSummaryVM getRelatedViewModel() {
        return (SessionSummaryVM) super.getRelatedViewModel();
    }

    @Override
    public void displaySearchResults() {
        super.displaySearchResults();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rcvSummary.setLayoutManager(mLayoutManager);
        binding.rcvSummary.setItemAnimator(new DefaultItemAnimator());
        binding.rcvSummary.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));

        sessionSummaryAdapter = new SessionSummaryAdapter(binding.rcvSummary, getRelatedViewModel().getSessionSummaryList(), this);
        binding.rcvSummary.setAdapter(sessionSummaryAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                /*Map<String, Object> params = new HashMap<>();
                params.put("ronda", getRelatedViewModel().getSession().getRonda());
                nextActivityFinishingCurrent(SessionListActivity.class, params);*/
                // Handle the back button click
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}