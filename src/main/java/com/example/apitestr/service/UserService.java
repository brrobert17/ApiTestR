package com.example.apitestr.service;

import com.example.apitestr.model.User;
import com.example.apitestr.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    final UserRepo userRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        Optional<User> result = userRepo.findUserByUsername(username);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("user not found by: " + username);
    }


    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> result = userRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("user not found by: " + id);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> result = userRepo.findById(id);
        if (result.isEmpty()) {
            throw new UserNotFoundException("user not found by: " + id);
        }
        userRepo.deleteById(id);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User updateUser(User updatedUser) throws UserNotFoundException {
        Long id = updatedUser.getId();
        Optional<User> result = userRepo.findById(id);
        if (result.isPresent()) {
            User old = result.get();
            old.setUsername(updatedUser.getUsername());
            old.setPassword(updatedUser.getPassword());
            old.setDetails(updatedUser.getDetails());
            userRepo.save(old);
            return old;
        }
        throw new UserNotFoundException("user not found by: " + id);
    }

}
