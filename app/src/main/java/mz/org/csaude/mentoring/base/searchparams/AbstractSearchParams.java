package mz.org.csaude.mentoring.base.searchparams;

import java.util.Date;

import mz.org.csaude.mentoring.base.model.BaseModel;

public abstract class AbstractSearchParams<T extends BaseModel> {

    private Date startdate;
    private Date endDate;


    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isByDateInterval(){
        return this.startdate != null || this.endDate != null;
    }

    public void resetParams(){
        this.startdate = null;
        this.endDate = null;
    }
}
