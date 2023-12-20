package com.ms.edu.users.controllers;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping(path = "/{id}")
    public String updateUser(@RequestBody User user, @PathVariable long id) {
        return userService.updateUser(user, id);
    }
}
