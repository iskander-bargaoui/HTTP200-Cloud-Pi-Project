package project.management.usersmanagement.Services;

import project.management.usersmanagement.entities.User;

import java.util.List;

public interface IUser {
    User addUser(User user);
    User updateUser(User user);
    User retrieveUserById(Long id);

    List<User> retrieveAllUsers();
    void deleteUser(Long id);

}