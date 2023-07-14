package mz.org.csaude.mentoring.model.session;

import java.util.Calendar;

public class SubmitedSessions {

    private String district;

    private String programmaticArea;

    private Long totalSubmited;

    private Calendar lastUpdate;

    public SubmitedSessions(String district, String programmaticArea, Long totalSubmited, Calendar lastUpdate) {
        this.district = district;
        this.programmaticArea = programmaticArea;
        this.totalSubmited = totalSubmited;
        this.lastUpdate = lastUpdate;
    }

    public SubmitedSessions() {
    }

    public String getDistrict() {
        return district;
    }

    public String getProgrammaticArea() {
        return programmaticArea;
    }

    public Long getTotalSubmited() {
        return totalSubmited;
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }
}
