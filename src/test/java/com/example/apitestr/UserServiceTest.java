package com.example.apitestr;

import com.example.apitestr.model.User;
import com.example.apitestr.repo.UserRepo;
import com.example.apitestr.service.UserNotFoundException;
import com.example.apitestr.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    @BeforeEach
    void setUp() {
        User user = new User("aaa", "000", "www");
        userRepo.deleteAll();
        userRepo.save(user);
    }

    @Test
    void addUser() throws UserNotFoundException {
        User newUser = new User("username", "password", "email");
        userService.saveUser(newUser);
        User user1 = userService.findUserByUsername("username");
        Assertions.assertThat(user1).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<User> users = userService.findAllUsers();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users
        ) {
            System.out.println(users);
        }
    }

    @Test
    public void testUpdate() throws UserNotFoundException {
        List<User> users = userService.findAllUsers();
        int userIndex = users.size() - 1;
        User user1 = users.get(userIndex);
        user1.setEmail("newEmail");
        userService.updateUser(user1);
        System.out.println(userService.findUserByEmail("newEmail"));
        Assertions.assertThat(userService.findUserByEmail("newEmail")).isNotNull();
    }

    @Test
    public void testGet() throws UserNotFoundException {
        Assertions.assertThat(userService.findUserById(1)).isNotNull();
    }

    @Test
    public void testDelete() {
        List<User> users = userService.findAllUsers();
        int userIndex = users.size() - 1;
        User user1 = users.get(userIndex);
        int id = user1.getId();
        userRepo.deleteById(id);
        Assertions.assertThat(userRepo.findById(id)).isNotPresent();
    }
}
