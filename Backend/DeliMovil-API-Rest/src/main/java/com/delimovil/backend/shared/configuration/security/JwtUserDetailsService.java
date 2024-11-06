package com.delimovil.backend.shared.configuration.security;

import com.delimovil.backend.models.entity.User;
import com.delimovil.backend.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Clase S2
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    //Obtener mi usuario, clave y sus roles.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);

        // Si hay un usuario con ese username
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        // Obtener los roles como GrantedAuthority
        List<GrantedAuthority> roles = new ArrayList<>();
        String role = user.getRole().getName();
        roles.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
