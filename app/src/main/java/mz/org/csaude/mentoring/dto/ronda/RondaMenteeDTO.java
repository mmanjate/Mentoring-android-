package mz.org.csaude.mentoring.dto.ronda;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.ronda.RondaMentee;
import mz.org.csaude.mentoring.model.rondatype.RondaType;
@Data
@NoArgsConstructor
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
    public RondaMentee getRondaMentee() {
        RondaMentee rondaMentee = new RondaMentee();
        rondaMentee.setId(this.getId());
        rondaMentee.setUuid(this.getUuid());
        rondaMentee.setSyncStatus(this.getSyncSatus());
        rondaMentee.setStartDate(this.getStartDate());
        rondaMentee.setEndDate(this.getEndDate());
        if(this.getMentee()!=null) {
            rondaMentee.setTutored(this.getRondaMentee().getTutored());
        }
        if(this.getRonda()!=null) {
            rondaMentee.setRonda(this.getRonda().getRonda());
        }
        return rondaMentee;
    }
}
