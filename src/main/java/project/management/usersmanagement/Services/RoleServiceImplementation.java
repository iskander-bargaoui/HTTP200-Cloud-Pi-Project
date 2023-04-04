package project.management.usersmanagement.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Repository.RoleRepository;
import project.management.usersmanagement.Config.UserRepository;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.entities.User;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class RoleServiceImplementation implements IRole {

    RoleRepository roleRepository;
    UserRepository userRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role retrieveRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);

    }

    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    /* @Override
    public Role assignRoleToUser(Long idUser, Long idRole) {
        Role role = roleRepository.findById(idRole).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        role.getUsers().add(user);
        return roleRepository.save(role);
    } */
}
