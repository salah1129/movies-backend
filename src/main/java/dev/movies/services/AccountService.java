package dev.movies.services;

import dev.movies.controllers.UserExeptions.UserNotFoundException;
import dev.movies.controllers.UserExeptions.RoleNotFoundException;

import dev.movies.DTO.RoleDTO;
import dev.movies.DTO.UserDTO;
import dev.movies.entities.RoleModel;
import dev.movies.entities.UserModel;
import dev.movies.entities.Movie;
import dev.movies.repositories.RoleRepository;
import dev.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public String addUser(UserModel user){
        userRepository.save(user);
        return "User registered successfully";
    }

    public Optional<UserDTO> getUserById(long id){
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            UserModel user = optionalUser.get();
            return Optional.of(new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getRoles().stream().map(Role -> Role.getRolename()).collect(Collectors.toList()),
                    user.getFavoriteMovies().stream().map(Movie::getId).collect(Collectors.toList())
            ));
        } else {
            return Optional.empty();
        }

    }
    public Optional<UserModel> loadUserByUsername(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRoles().stream().map(Role -> Role.getRolename()).collect(Collectors.toList()),
                        user.getFavoriteMovies().stream().map(movie -> movie.getId()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }




    public String addRole(RoleModel role){
        roleRepository.save(role);
        return "role added successfully";
    }

    public List<RoleDTO> getAllRoles(){

        return roleRepository.findAll()
                .stream()
                .map(role -> new RoleDTO(
                        role.getId(),
                        role.getRolename()
                ))
                .collect(Collectors.toList());

    }

    public Optional<RoleDTO> getRoleById(long id) {
        Optional<RoleModel> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            RoleModel role = optionalRole.get();
            return Optional.of(new RoleDTO(
                    role.getId(),
                    role.getRolename()
            ));
        } else {
            return Optional.empty();
        }
    }

    public Optional<RoleModel> loadRoleByRolename(String rolename){
        return Optional.ofNullable(roleRepository.findByRolename(rolename));
    }
    public void addRoleToUser(String username, String rolename){
        UserModel user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found.");
        }

        RoleModel role = roleRepository.findByRolename(rolename);

        if (role == null) {
            throw new RoleNotFoundException("Role with rolename " + rolename + " not found.");
        }

        user.getRoles().add(role);
    }


}
