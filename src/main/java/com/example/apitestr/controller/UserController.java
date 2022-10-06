package com.example.apitestr.controller;

import com.example.apitestr.model.User;
import com.example.apitestr.service.UserNotFoundException;
import com.example.apitestr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    public List<User> showUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/find")
    public User showUserById(@RequestParam int id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

    @PostMapping
    public User updateUser(@RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(user);
    }

    @PostMapping("/save")
    public User saveNewUser(@RequestBody User user) throws UserNotFoundException {
        userService.saveUser(user);
        return userService.findUserById(user.getId());
    }

    @GetMapping("/delete")
    public User deleteUser(@RequestParam int id) throws UserNotFoundException {
       User user = userService.findUserById(id);
        userService.deleteUserById(id);
        return user;
    }
}
