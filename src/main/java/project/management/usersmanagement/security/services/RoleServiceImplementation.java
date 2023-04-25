package project.management.usersmanagement.security.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Repository.UserRepository;
import project.management.usersmanagement.entities.ERole;
import project.management.usersmanagement.entities.User;
import project.management.usersmanagement.repository.RoleRepository;
import project.management.usersmanagement.entities.Role;

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

    @Override
    public Role assignRoleToUser(String username, String roleName) {
        User u = userRepository.findByUsername(username).orElse(null);
        Role r = roleRepository.findByName(ERole.valueOf(roleName)).orElse(null);
        u.getRoles().add(r);
        return roleRepository.save(r);
    }

    /* @Override
    public Role assignRoleToUser(Long idUser, Long idRole) {
        Role role = roleRepository.findById(idRole).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        role.getUsers().add(user);
        return roleRepository.save(role);
    } */
}
