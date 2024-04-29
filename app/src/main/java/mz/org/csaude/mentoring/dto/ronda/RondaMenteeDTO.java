package mz.org.csaude.mentoring.dto.ronda;

import java.util.Date;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

public class RondaMenteeDTO extends BaseEntityDTO {
    private Date startDate;
    private Date endDate;
    private TutoredDTO mentee;
    private RondaDTO ronda;
    public RondaMenteeDTO(RondaMentee rondaMentee) {
        super(rondaMentee);
        this.setMentee(new TutoredDTO(rondaMentee.getTutored()));
        this.setRonda(new RondaDTO(rondaMentee.getRonda()));
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TutoredDTO getMentee() {
        return mentee;
    }

    public void setMentee(TutoredDTO mentee) {
        this.mentee = mentee;
    }

    public RondaDTO getRonda() {
        return ronda;
    }

    public void setRonda(RondaDTO ronda) {
        this.ronda = ronda;
    }
}
