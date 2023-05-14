package com.sanreg.sanatorium.service;

import com.sanreg.sanatorium.dto.RegistrationDto;
import com.sanreg.sanatorium.entity.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    boolean activateUser(String code);

}
