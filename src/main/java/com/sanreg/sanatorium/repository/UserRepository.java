package com.sanreg.sanatorium.repository;

import com.sanreg.sanatorium.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>  {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    UserEntity findFirstByUsername(String username);
    UserEntity findByActivationCode(String code);
}
