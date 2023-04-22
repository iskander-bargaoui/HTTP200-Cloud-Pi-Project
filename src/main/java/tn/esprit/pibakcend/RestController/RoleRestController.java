package project.management.usersmanagement.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.security.services.IRole;
import project.management.usersmanagement.entities.Role;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class RoleRestController {

    @Autowired
    IRole iRole;

    // URL : http://localhost:8080/role/addRole
    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        return iRole.addRole(role);
    }

    @PutMapping("/updateRole")
    public Role updateSkieur(@RequestBody Role role ){
        return iRole.updateRole(role);
    }

    @GetMapping("/getRoleById/{id}")
    public Role retrieveUserById(@PathVariable("id") Long id){
        return iRole.retrieveRoleById(id);
    }

    @GetMapping("/getAllRoles")
    public List<Role> retrieveAllRoles(){
        return iRole.retrieveAllRoles();
    }

    @DeleteMapping("/delRole/{id}")
    public void deleteRole (@PathVariable("id") Long id){
        iRole.deleteRole(id);
    }

    // Affecter un rôle à un utilisasteur
    @PostMapping("/assignRoleToUser/{username}/{roleName}")
    public Role assignSkieurToPiste(@PathVariable("username") String username, @PathVariable("roleName") String roleName)
    {
        return iRole.assignRoleToUser(username,roleName);
    }


}
