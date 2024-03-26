package mz.org.csaude.mentoring.view.session;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.spinner.listble.ListableSpinnerAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySessionListBinding;
import mz.org.csaude.mentoring.view.ronda.RondaActivity;
import mz.org.csaude.mentoring.viewmodel.session.SessionListVM;

public class SessionListActivity extends BaseActivity {

    private ActivitySessionListBinding sessionListBinding;

    private ListableSpinnerAdapter menteeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionListBinding = DataBindingUtil.setContentView(this, R.layout.activity_session_list);
        sessionListBinding.setViewModel(getRelatedViewModel());

        initAdapters();
    }

    private void initAdapters() {
        menteeAdapter = new ListableSpinnerAdapter(SessionListActivity.this, R.layout.simple_auto_complete_item, getRelatedViewModel().getRondaMentees());
        sessionListBinding.spnMentees.setAdapter(menteeAdapter);
        sessionListBinding.setMenteesAdapter(menteeAdapter);
    }


    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SessionListVM.class);
    }

    @Override
    public SessionListVM getRelatedViewModel() {
        return (SessionListVM) super.getRelatedViewModel();
    }
}