package project.management.usersmanagement.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Repository.RoleRepository;
import project.management.usersmanagement.Config.UserRepository;
import project.management.usersmanagement.entities.User;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class UserServiceImplementation implements IUser {


    UserRepository userRepository;
    RoleRepository roleRepository;

    @Override
    public User addUser(User user) { return userRepository.save(user); }

    @Override
    public User updateUser(User user) { return userRepository.save(user); }

    @Override
    public User retrieveUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
