package project.management.usersmanagement.Services;

import project.management.usersmanagement.entities.Role;

import java.util.List;

public interface IRole {

    Role addRole(Role role);
    Role updateRole(Role role);
    Role retrieveRoleById(Long id);

    List<Role> retrieveAllRoles();
    void deleteRole(Long id);
    /* Role assignRoleToUser(Long idUser, Long idRole); */

}
