package mz.org.csaude.mentoring.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.employee.EmployeeDTO;
import mz.org.csaude.mentoring.model.user.User;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntityDTO {

    private String username;

    private String password;

    private String salt;

    private EmployeeDTO employeeDTO;

    public UserDTO(User user) {
        super(user);
        this.setEmployeeDTO(new EmployeeDTO(user.getEmployee()));
        this.setUsername(user.getUserName());
        this.setPassword(user.getPassword());
        this.setSalt(user.getSalt());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
}
