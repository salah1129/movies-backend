package dev.movies.controllers;

import dev.movies.DTO.RoleDTO;
import dev.movies.DTO.UserDTO;
import dev.movies.entities.RoleModel;
import dev.movies.entities.UserModel;
import dev.movies.services.AccountService;
import dev.movies.controllers.UserExeptions.UserNotFoundException;
import dev.movies.controllers.UserExeptions.RoleNotFoundException;import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/users")
    public String addUser(@RequestBody UserModel user){
        accountService.addUser(user);
        return "User registered successfully";
    }
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return  accountService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<UserDTO> getUserById(@PathVariable long id){
        return accountService.getUserById(id);
    }


    @PostMapping("/roles")
    public String addRole(@RequestBody RoleModel role){
        accountService.addRole(role);
        return "Role added successfully";
    }
    @GetMapping("/roles")
    public List<RoleDTO> getRoles(){
        return accountService.getAllRoles();
    }
    @GetMapping("/roles/{id}")
    public Optional<RoleDTO> getRoleById(@PathVariable long id){
        return accountService.getRoleById(id);
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        try {
            accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRolename());
            return ResponseEntity.ok("Role added successfully to the user.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not available: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}

@Data
class RoleUserForm{
    private String username;
    private String rolename;
}
