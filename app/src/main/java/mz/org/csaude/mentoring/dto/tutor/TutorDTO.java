package mz.org.csaude.mentoring.dto.tutor;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.dto.partner.PartnerDTO;
import mz.org.csaude.mentoring.dto.user.UserDTO;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutored.Tutored;
@Data
@NoArgsConstructor
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
        tutor.setId(this.getId());
        tutor.setUuid(this.getUuid());
        tutor.setSyncStatus(this.getSyncSatus());
        if(this.getEmployeeDTO()!=null) {
            tutor.setEmployee(this.getEmployeeDTO().getEmployee());
        }
        return tutor;
    }
}
