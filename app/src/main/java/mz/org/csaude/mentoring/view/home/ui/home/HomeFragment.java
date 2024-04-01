package mz.org.csaude.mentoring.view.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import mz.org.csaude.mentoring.base.fragment.GenericFragment;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.FragmentHomeBinding;
import mz.org.csaude.mentoring.viewmodel.home.HomeVM;
import mz.org.csaude.mentoring.viewmodel.home.HomeFragmentViewModel;

public class HomeFragment extends GenericFragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setViewModel(getRelatedViewModel());
    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(HomeFragmentViewModel.class);
    }

    @Override
    public HomeFragmentViewModel getRelatedViewModel() {
        return (HomeFragmentViewModel) super.getRelatedViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}