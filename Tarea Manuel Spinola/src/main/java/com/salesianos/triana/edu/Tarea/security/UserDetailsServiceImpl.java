package com.salesianos.triana.edu.Tarea.security;

import com.salesianos.triana.edu.Tarea.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService usuarioServicio;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioServicio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No hay usuarios con username: " + username));
    }

}
