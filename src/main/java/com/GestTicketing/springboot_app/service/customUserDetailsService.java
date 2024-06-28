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
        if (user == null){
            throw new UsernameNotFoundException("Aucun user n'existe avec ce identifiant: " + username);

        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()
                )));
    }


}
