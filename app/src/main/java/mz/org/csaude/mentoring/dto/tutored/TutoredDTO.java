package mz.org.csaude.mentoring.dto.tutored;

import java.io.Serializable;

import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.tutored.Tutored;

/**
 * @author Jose Julai Ritsure
 */
public class TutoredDTO implements Serializable {

    private String uuid;

    private EmployeeDTO employeeDTO;

    public TutoredDTO() {
    }

    public TutoredDTO(Tutored tutored) {
        this.setUuid(tutored.getUuid());
        this.setEmployeeDTO(new EmployeeDTO(tutored.getEmployee()));
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
}
