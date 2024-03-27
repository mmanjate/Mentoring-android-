package mz.org.csaude.mentoring.dto.user;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.model.user.UserIndividual;


public class UserDTO {

    private String username;

    private String password;

    private String salt;

    private EmployeeDTO employeeDTO;



    public UserDTO(User user) {
        super(user);
        this.setEmployeeDTO(new EmployeeDTO(user.getEmployee()));
        this.setUsername(user.getUsername());
        this.setSalt(user.getSalt());
    }

    private List<UserRoleDTO> setUserRoles(List<UserRole> roleSet) {
        List<UserRoleDTO> roleDTOSet = new ArrayList<>();

        for (UserRole userRole : roleSet) {
            roleDTOSet.add(new UserRoleDTO(userRole));
        }
        return roleDTOSet;
    }


}
