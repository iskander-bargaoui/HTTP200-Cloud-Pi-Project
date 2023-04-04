package project.management.usersmanagement.Auth;


import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Config.JwtService;
import project.management.usersmanagement.Config.UserRepository;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.entities.User;
import project.management.usersmanagement.token.Token;
import project.management.usersmanagement.token.TokenRepository;
import project.management.usersmanagement.token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User user) {
   /* var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .autority(naa)//autority(na)
        .build();*/
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public Authentication authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("Hello 1 !!");
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("I Cant found this User- "));
        System.out.println("Hello 2!!");

        List<GrantedAuthority> authorities = getAuthorities(user.getAuthFromBase());
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        return new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> autoritys) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role auth : autoritys) {
            list.add(new SimpleGrantedAuthority(auth.getName().name().toString()));

        }
        return list;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
