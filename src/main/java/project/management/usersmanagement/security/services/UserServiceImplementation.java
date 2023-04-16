package project.management.usersmanagement.security.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.entities.ERole;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.repository.RoleRepository;
import project.management.usersmanagement.repository.UserRepository;
import project.management.usersmanagement.entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

    public class UserServiceImplementation implements IUser, UserDetailsService{

    UserRepository userRepository;
    RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
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

    @Override
    public void addRoleToUser(String username, ERole roleName) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        user.get().getRoles().add(role.get());
    }

    @Override
    public boolean ifEmailExist(String email) {
        return false;
    }

    @Override
    public User getUserByMail(String mail) {
        return null;
    }

    @Override
    public List<User> retrieveUserByAddress(String adressUser) {
        return null;
    }

    @Override
    @Scheduled(fixedDelay = 1000)

    public long retrieveUserByCount() {
        log.info("nb users : "+userRepository.count());
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            log.error("user {} not found in the database", username);
            throw new UsernameNotFoundException("user not found");

        } else {
            log.info("user {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(),
                authorities);
    }
}
