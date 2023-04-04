package project.management.usersmanagement.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.Services.IRole;
import project.management.usersmanagement.entities.Role;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class RoleRestController {

    @Autowired
    IRole iRole;

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

}
