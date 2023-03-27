package com.sanreg.sanatorium.security;

import com.sanreg.sanatorium.entity.UserEntity;
import com.sanreg.sanatorium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null && userEntity.isActive()){
            User authUser = new User(userEntity.getUsername(),userEntity.getPassword(),
                    userEntity.getRoles().stream().map((role -> new SimpleGrantedAuthority(role.getName()))).collect(Collectors.toList()));
            return authUser;
        } else {throw new UsernameNotFoundException("Неверный логин или пароль");}
    }

}
