package com.springboot2.api.user.repository;

import java.util.Optional;

import com.springboot2.api.user.domain.Role;
import com.springboot2.api.user.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
