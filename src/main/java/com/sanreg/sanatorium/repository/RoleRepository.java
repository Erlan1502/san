package com.sanreg.sanatorium.repository;

import com.sanreg.sanatorium.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
