package mz.org.csaude.mentoring.dto.tutored;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.tutored.Tutored;

/**
 * @author Jose Julai Ritsure
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutoredDTO extends BaseEntityDTO {

    private EmployeeDTO employeeDTO;

    private boolean zeroEvaluationDone;

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

    public boolean isZeroEvaluationDone() {
        return zeroEvaluationDone;
    }

    public void setZeroEvaluationDone(boolean zeroEvaluationDone) {
        this.zeroEvaluationDone = zeroEvaluationDone;
    }

    public Tutored getMentee() {
        Tutored tutored = new Tutored();
        tutored.setUuid(this.getUuid());
        tutored.setCreatedAt(this.getCreatedAt());
        tutored.setUpdatedAt(this.getUpdatedAt());
        tutored.setLifeCycleStatus(this.getLifeCycleStatus());
        if(this.getEmployeeDTO()!=null) {
            tutored.setEmployee(this.getEmployeeDTO().getEmployee());
        }
        return tutored;
    }
}
