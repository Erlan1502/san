package com.sanreg.sanatorium.service;

import com.sanreg.sanatorium.dto.RegistrationDto;
import com.sanreg.sanatorium.entity.Role;
import com.sanreg.sanatorium.entity.UserEntity;
import com.sanreg.sanatorium.repository.RoleRepository;
import com.sanreg.sanatorium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService{
    @Value("${host}")
    private String host;
    private MailSender mailSender;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImplementation(MailSender mailSender, UserRepository userRepository,
                                     RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userEntity.setEmail(registrationDto.getEmail());
        Role role = roleRepository.findByName("ROLE_PATIENT");
        userEntity.setRoles(Arrays.asList(role));
        userEntity.setActive(false);
        userEntity.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(userEntity);

        if(!StringUtils.isEmpty(userEntity.getEmail())){
            String mailMessage = String.format("Добрый день %s" + "\n" +"пройдите по ссылке для активации аккаунта : " +
                    host +"/register/activate/%s" ,userEntity.getUsername(),userEntity.getActivationCode());
            mailSender.send(userEntity.getEmail(),"Код активации", mailMessage);
        }

    }


    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean activateUser(String code) {
        UserEntity userEntity = userRepository.findByActivationCode(code);
        if(userEntity ==null){
            return false;
        }
        userEntity.setActive(true);
        userEntity.setActivationCode(null);
        userRepository.save(userEntity);
        return true;
    }



}
