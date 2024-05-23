package mz.org.csaude.mentoring.view.tutored;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityTutoredBinding;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.view.tutored.fragment.TutoredFragment;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

public class TutoredActivity extends BaseActivity {

    ActivityTutoredBinding tutoredBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutoredBinding = DataBindingUtil.setContentView(this, R.layout.activity_tutored);
        tutoredBinding.setViewModel(getRelatedViewModel());

        Intent intent = this.getIntent();
        Bundle bundle = new Bundle();
        if(intent!=null && intent.getExtras()!=null) {
            Tutored tutored = (Tutored) intent.getExtras().get("createdTutored");
            bundle.putSerializable("createdTutored", tutored);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.tutored_fragment, TutoredFragment.class, bundle)
                    .commit();
        }

        setUpToolbar();
        getRelatedViewModel().setViewListEditButton(false);
        getRelatedViewModel().setViewListRemoveButton(false);

    }

    private void setUpToolbar() {
        setSupportActionBar(tutoredBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Mentorandos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.utilities_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_upload:
                getRelatedViewModel().initMenteeUpload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(TutoredVM.class);
    }

    @Override
    public TutoredVM getRelatedViewModel() {
        return (TutoredVM) super.getRelatedViewModel();
    }
}
