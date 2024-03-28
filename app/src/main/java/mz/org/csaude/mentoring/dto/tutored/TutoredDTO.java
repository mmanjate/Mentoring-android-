package mz.org.csaude.mentoring.dto.tutored;

import java.io.Serializable;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.tutored.Tutored;

/**
 * @author Jose Julai Ritsure
 */
public class TutoredDTO extends BaseEntityDTO {

    private EmployeeDTO employeeDTO;

    public TutoredDTO() {
    }

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
}
