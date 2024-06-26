package mz.org.csaude.mentoring.view.resource;



import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.adapter.resource.ResourceAdapter;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivityResourceBinding;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.resourceea.Resource;
import mz.org.csaude.mentoring.viewmodel.resource.ResourceVM;

public class ResourceActivity extends BaseActivity {

    ActivityResourceBinding activityResourceBinding;

    private RecyclerView rcvResources;

    private ResourceAdapter resourceAdapter;
    List<Resource> resourceList = new ArrayList<>();
    List<Node> nodeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityResourceBinding = DataBindingUtil.setContentView(this , R.layout.activity_resource);
        activityResourceBinding.setViewModel(this.getRelatedViewModel());
        Intent intent = this.getIntent();

        this.rcvResources = activityResourceBinding.rcvResources;

        if(intent!=null && intent.getExtras()!=null) {
            //setUpToolbar();
        }
        setUpToolbar();
        initAdapter();
    }

    private void setUpToolbar() {

        setSupportActionBar(activityResourceBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Resources EA");
        getRelatedViewModel().setViewListEditButton(false);
        getRelatedViewModel().setViewListRemoveButton(false);

    }

    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(ResourceVM.class);
    }

    @Override
    public ResourceVM getRelatedViewModel() {
        return (ResourceVM) super.getRelatedViewModel();
    }

    public void initAdapter() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvResources.setLayoutManager(mLayoutManager);
        //rcvResources.setItemAnimator(new DefaultItemAnimator());
        //rcvResources.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
        this.resourceList = this.getRelatedViewModel().getAllResource();
        convertStringtoJson();
        String resourceString = this.resourceList.get(0).getResource();
        resourceAdapter = new ResourceAdapter(rcvResources,  this.nodeList, this);
        rcvResources.setAdapter(resourceAdapter);
    }

    private void convertStringtoJson(){
        List<Node> nodeArrayList = new ArrayList<>();

        String resourceString = this.resourceList.get(0).getResource();
        try {
            ObjectMapper mapper = new ObjectMapper();
            nodeArrayList = Arrays.asList(mapper.readValue(resourceString, Node[].class));

            for(Node nodeProgram : nodeArrayList) {
                if (nodeProgram.getChildren() != null) {
                    for (Node nodeCateg : nodeProgram.getChildren()) {
                        if (nodeCateg.getChildren() != null) {
                            for (Node nodeSubCategory : nodeCateg.getChildren()) {
                                if (nodeSubCategory.getChildren() != null) {
                                    for (Node nodeResource : nodeSubCategory.getChildren()) {
                                        nodeResource.setProgram(nodeSubCategory.getChildren().get(nodeSubCategory.getChildren().size() - 1).getProgram());
                                        nodeResource.setSubCategory(nodeSubCategory.getChildren().get(nodeSubCategory.getChildren().size() - 1).getSubCategory());
                                        this.nodeList.add(nodeResource);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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