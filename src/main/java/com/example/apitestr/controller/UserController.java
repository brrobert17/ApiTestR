package com.example.apitestr.controller;

import com.example.apitestr.model.User;
import com.example.apitestr.service.UserNotFoundException;
import com.example.apitestr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/users")
    public List<User> showUsers() {
        return userService.findAllUsers();
    }

    @GetMapping
    public User showUserById(@RequestParam Long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(user);
    }

    @PostMapping
    public User saveNewUser(@RequestBody User user) throws UserNotFoundException {
        userService.saveUser(user);
        return userService.findUserById(user.getId());
    }

    @DeleteMapping
    public User deleteUser(@RequestParam Long id) throws UserNotFoundException {
       User user = userService.findUserById(id);
        userService.deleteUserById(id);
        return user;
    }
}
