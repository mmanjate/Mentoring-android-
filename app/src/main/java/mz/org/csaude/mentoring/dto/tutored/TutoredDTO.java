package mz.org.csaude.mentoring.dto.tutored;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.util.SyncSatus;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
public class TutoredDTO extends BaseEntityDTO {

    private EmployeeDTO employeeDTO;

    public TutoredDTO(Tutored tutored) {
        super(tutored);
        this.setEmployeeDTO(new EmployeeDTO(tutored.getEmployee()));
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Tutored getMentee() {
        Tutored tutored = new Tutored();
        tutored.setId(this.getId());
        tutored.setUuid(this.getUuid());
        tutored.setSyncStatus(this.getSyncSatus());
        tutored.setCreatedAt(this.getCreatedAt());
        tutored.setUpdatedAt(this.getUpdatedAt());
        if(this.getEmployeeDTO()!=null) {
            tutored.setEmployee(this.getEmployeeDTO().getEmployee());
        }
        return tutored;
    }
}
