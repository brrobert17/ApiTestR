package com.example.apitestr.repo;

import com.example.apitestr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Integer countById(Integer id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

}
