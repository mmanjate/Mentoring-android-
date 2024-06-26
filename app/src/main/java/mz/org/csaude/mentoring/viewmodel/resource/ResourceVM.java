package mz.org.csaude.mentoring.viewmodel.resource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.BR;
import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.listner.rest.ServerStatusListener;
import mz.org.csaude.mentoring.model.resourceea.Node;
import mz.org.csaude.mentoring.model.resourceea.Resource;
import mz.org.csaude.mentoring.service.resource.ResourceService;
import mz.org.csaude.mentoring.util.Utilities;


public class ResourceVM extends BaseViewModel implements RestResponseListener<Resource>, ServerStatusListener {

    private Resource resource;

    private Node node;

    private ResourceService resourceService;

    private List<Resource> resources;
    private List<Node> Nodes;

    public ResourceVM(@NonNull Application application) {
        super(application);
        resourceService = getApplication().getResourceService();
        resources = new ArrayList<>();
    }

    @Override
    public void preInit() {
       this.resource = new Resource();
    }

    public List<Resource> getAllResource(){
        try {
            return this.resourceService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Bindable
    public String getLabel() {
        return this.node.getLabel();
    }

    public void setLabel(String label) {
        this.node.setLabel(label);
        notifyPropertyChanged(BR.label);
    }
    @Bindable
    public int getClickable() {
        return this.node.getClickable();
    }

    public void setClickable(int clickable) {
        this.node.setClickable(clickable);
        notifyPropertyChanged(BR.clickable);
    }

    @Bindable
    public List<Node> getChildren() {
        return this.node.getChildren();
    }

    public void setChildren(List<Node> children) {
        this.node.setChildren(children);
    }

    @Bindable
    public String getIcon() {
        return this.node.getIcon();
    }

    public void setIcon(String icon) {
        this.node.setIcon(icon);
    }

    @Bindable
    public String getProgram() {
        return this.node.getProgram();
    }

    @Bindable
    public void setProgram(String program) {
        this.node.setProgram(program);
    }

    @Bindable
    public String getType() {
        return this.node.getType();
    }

    public void setType(String type) {
        this.node.setType(type);
    }

    @Bindable
    public String getCategory() {
        return this.node.getCategory();
    }

    public void setCategory(String category) {
        this.node.setCategory(category);
    }

    @Bindable
    public String getSubCategory() {
        return this.node.getSubCategory();
    }

    public void setSubCategory(String subCategory) {
        this.node.setSubCategory(subCategory);
    }

    @Bindable
    public String getResource() {
        return this.node.getResource();
    }
    public void setResource(String resource) {
        this.node.setResource(resource);
        notifyPropertyChanged(BR.resource);
    }
    @Override
    public void onServerStatusChecked(boolean isOnline) {

        if(isOnline){
            //getApplication().getResourceRestService().
        }else {
            Utilities.displayAlertDialog(getRelatedActivity(), getRelatedActivity().getString(R.string.server_unavailable)).show();
        }


    }
}
