package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.config.JWTUserData;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email/password incorrect"));
    }

    public JWTUserData getUser(){
        return (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
