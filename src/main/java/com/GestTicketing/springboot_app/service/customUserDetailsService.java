package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class customUserDetailsService implements UserDetailsService {
    //injection du userRepository
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Aucun utilisateur trouvé avec le nom d'utilisateur: " + username);
        }

        // Vérifier le type d'utilisateur et créer un UserDetails approprié
        switch (user.getRole()) {
            case "APPRENANT":
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_APPRENANT")));
            case "FORMATEUR":
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_FORMATEUR")));
            case "ADMIN":
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            default:
                throw new UsernameNotFoundException("Ce Rôle utilisateur n'existe pas : " + username);
        }
    }
}