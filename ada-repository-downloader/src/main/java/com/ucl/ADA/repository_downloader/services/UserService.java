package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for listing and deleting users in the database.
 */

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public List<User> listUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
