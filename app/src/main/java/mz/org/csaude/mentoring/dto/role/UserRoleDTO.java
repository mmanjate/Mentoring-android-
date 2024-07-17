package mz.org.csaude.mentoring.dto.role;


import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.dto.user.UserDTO;

public class UserRoleDTO extends BaseEntityDTO {

    private UserDTO userDTO;

    private RoleDTO roleDTO;

    public UserRoleDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
