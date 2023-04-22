package tn.esprit.pibakcend.security.services;

import tn.esprit.pibakcend.entities.ERole;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface IUser {
    User addUser(User user);
    User updateUser(User user);
    User retrieveUserById(Long id);
    List<User> retrieveAllUsers();
    void deleteUser(Long id);
    // void forgotpass(String emailuser);
    void addRoleToUser(String username, ERole roleName);

    boolean ifEmailExist(String email);

    User getUserByMail(String mail);

    //**********Partie Statistiques***********
    List<User> retrieveUserByAddress(String adressUser);
    public long retrieveUserByCount();

    // Mot de passe à jour
    String updatePassword(String emailUser, String newPassword, String confirmPassword);
    // Mot de passe oublié
    void forgotpass(String emailuser);

}