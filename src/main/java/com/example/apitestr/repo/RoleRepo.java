package com.example.apitestr.repo;

import java.util.Optional;

import com.example.apitestr.model.ERole;
import com.example.apitestr.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
