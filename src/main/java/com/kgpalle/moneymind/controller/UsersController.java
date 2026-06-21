package com.kgpalle.moneymind.controller;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.entity.User;
import com.kgpalle.moneymind.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/signup")
    public UserDTO createUser(@RequestBody User user) {
        return usersService.create(user);
    }
}
