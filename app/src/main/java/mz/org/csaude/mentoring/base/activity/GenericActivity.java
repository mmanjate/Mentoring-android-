package mz.org.csaude.mentoring.base.activity;

import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;

public interface GenericActivity {

    /**
     * Initialize a new {@link BaseViewModel}
     *
     * @return an instance os {@link BaseViewModel}
     */
    BaseViewModel initViewModel();
}
