package project.management.usersmanagement.security.services;

import project.management.usersmanagement.entities.Role;

import java.util.List;

public interface IRole {

    Role addRole(Role role);
    Role updateRole(Role role);
    Role retrieveRoleById(Long id);

    List<Role> retrieveAllRoles();
    void deleteRole(Long id);
    Role assignRoleToUser(String username, String roleName);
    /* Role assignRoleToUser(Long idUser, Long idRole); */

}
