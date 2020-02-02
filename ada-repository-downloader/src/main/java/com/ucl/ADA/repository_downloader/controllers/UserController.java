package com.ucl.ADA.repository_downloader.controllers;


import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for listing and deleting users/owners of Git repos in the database.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserService userService;

    @DeleteMapping
    public void deleteAll(){ userService.deleteAllUsers();}

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){ userService.deleteUser(id);}

    @GetMapping
    public List<User> listAllUsers() {
        return userService.listUsers();
    }
}
