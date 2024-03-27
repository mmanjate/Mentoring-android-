package mz.org.csaude.mentoring.view.tutored.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.tutored.TutoredAdapter;
import mz.org.csaude.mentoring.base.fragment.GenericFragment;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.FragmentTutoredsBinding;
import mz.org.csaude.mentoring.listner.dialog.IListbleDialogListener;
import mz.org.csaude.mentoring.listner.recyclerView.ClickListener;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.view.tutored.CreateTutoredActivity;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Julai Ritsure
 */
public class TutoredFragment extends GenericFragment implements IListbleDialogListener {
    private FragmentTutoredsBinding fragmentTutoredBinding;

    private RecyclerView rcvTutoreds;

    private List<Tutored> tutoreds;

    private TutoredAdapter tutoredItemAdapter;

    public TutoredFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTutoredBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tutoreds, container, false);
        return fragmentTutoredBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rcvTutoreds = fragmentTutoredBinding.rcvTutoreds;
        this.tutoreds = new ArrayList<>();
        try {
            this.tutoreds = getRelatedViewModel().getAllTutoreds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Tutored tutored = (Tutored) savedInstanceState.get("createdTutored");
        //this.tutoreds.add(tutored);

        if (Utilities.listHasElements(this.tutoreds)) {
            this.tutoredItemAdapter = new TutoredAdapter(rcvTutoreds, this.tutoreds, getMyActivity());
            displayDataOnRecyclerView(rcvTutoreds, tutoredItemAdapter, getContext());
        }

        fragmentTutoredBinding.newTutored.setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View view) {
                                                                     Intent intent = new Intent(getContext(), CreateTutoredActivity.class);
                                                                     Bundle bundle = new Bundle();
                                                                     bundle.putSerializable("user", getCurrentUser());
                                                                     intent.putExtras(bundle);
                                                                     startActivity(intent);
                                                                 }
                                                             }
        );

        rcvTutoreds.addOnItemTouchListener(new ClickListener(getContext(), rcvTutoreds, new ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                displayPopupMenu(view, position);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                displayPopupMenu(view, position);
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.rcvTutoreds = fragmentTutoredBinding.rcvTutoreds;

        try {
            this.tutoreds = getRelatedViewModel().getAllTutoreds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (Utilities.listHasElements(tutoreds)) {
            tutoredItemAdapter = new TutoredAdapter(rcvTutoreds, this.tutoreds, getMyActivity());
            displayDataOnRecyclerView(rcvTutoreds, tutoredItemAdapter, getContext());
        } else if (getMyActivity().getPositionRemoved() != null) {
            tutoredItemAdapter = new TutoredAdapter(rcvTutoreds, this.tutoreds, getMyActivity());
            tutoredItemAdapter.notifyItemRangeRemoved(0, tutoreds.size());

        }
    }

    private TutoredVM getRelatedViewModel() {
        return (TutoredVM) getMyActivity().getRelatedViewModel();
    }

    private void displayPopupMenu(View view, int position) {
        getRelatedViewModel().setTutored(tutoreds.get(position));
        getRelatedViewModel().getTutored().setListPosition(position);

        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(TutoredFragment.this::onMenuItemClick);
        inflater.inflate(R.menu.edit_remove_menu, popup.getMenu());
        popup.getMenu().getItem(0).setVisible(true);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                try {
                    Tutored tutored = getRelatedViewModel().getTutored();
                    getRelatedViewModel().deleteTutored(tutored);
                    getTutoreds().remove(tutored);
                    setTutoreds(getTutoreds());
                    Utilities.displayAlertDialog(TutoredFragment.this.getContext(), getString(R.string.record_sucessfully_removed)).show();
                    tutoredItemAdapter = new TutoredAdapter(rcvTutoreds, this.tutoreds, getMyActivity());
                    displayDataOnRecyclerView(rcvTutoreds, tutoredItemAdapter, getContext());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            default:
                return false;
        }
    }

    @Override
    public void remove(int position) {

        String errorMsg = getRelatedViewModel().tutoredHasSessions();

        if (!Utilities.stringHasValue(errorMsg)) {
            try {
                tutoreds.remove(getRelatedViewModel().getTutored());
                rcvTutoreds.getAdapter().notifyItemRemoved(position);
                rcvTutoreds.removeViewAt(position);
                rcvTutoreds.getAdapter().notifyItemRangeChanged(position, rcvTutoreds.getAdapter().getItemCount());
                getRelatedViewModel().deleteTutored(getRelatedViewModel().getTutored());
            } catch (SQLException e) {
                Utilities.displayAlertDialog(TutoredFragment.this.getContext(), getString(R.string.record_sucessfully_removed)).show();
            }

        } else {
            Utilities.displayAlertDialog(TutoredFragment.this.getContext(), errorMsg).show();
        }
    }

    @Override
    public void remove(BaseModel baseModel) {

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(TutoredVM.class);
    }

    public List<Tutored> getTutoreds() {
        return this.tutoreds;
    }

    public void setTutoreds(List<Tutored> tutoreds) {
        this.tutoreds = tutoreds;
    }


}
