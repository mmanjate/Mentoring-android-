package mz.org.csaude.mentoring.dto.tutor;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.tutor.Tutor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorDTO extends BaseEntityDTO {

    private EmployeeDTO employeeDTO;

    public TutorDTO(Tutor tutor) {
        super(tutor);
        this.setEmployeeDTO(new EmployeeDTO(tutor.getEmployee()));
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Tutor getTutor() {
        Tutor tutor = new Tutor();
        tutor.setUuid(this.getUuid());
        tutor.setCreatedAt(this.getCreatedAt());
        tutor.setUpdatedAt(this.getUpdatedAt());
        tutor.setLifeCycleStatus(this.getLifeCycleStatus());
        if(this.getEmployeeDTO()!=null) {
            tutor.setEmployee(this.getEmployeeDTO().getEmployee());
        }
        return tutor;
    }
}
