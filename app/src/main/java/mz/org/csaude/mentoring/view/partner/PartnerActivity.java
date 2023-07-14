package mz.org.csaude.mentoring.view.partner;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityPartnerBinding;
import mz.org.csaude.mentoring.viewmodel.partner.PartnerVM;

public class PartnerActivity extends BaseActivity {

    ActivityPartnerBinding partnerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        partnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_tutored);

        partnerBinding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(PartnerVM.class);
    }

    @Override
    public PartnerVM getRelatedViewModel() {
        return (PartnerVM) super.getRelatedViewModel();
    }
}
