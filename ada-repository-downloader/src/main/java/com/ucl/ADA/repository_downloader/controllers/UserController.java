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
@Controller
public class UserController {

    @Autowired private UserService userService;

    @DeleteMapping("/deleteAllUsers")
    @ResponseBody
    public void deleteAll(){ userService.deleteAllUsers();}

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public void deleteUser(@RequestParam(name = "name") String userName){ userService.deleteUser(userName);}

    @GetMapping("/allUsers")
    @ResponseBody
    public List<User> listAllUsers() {
        return userService.listUsers();
    }
}
