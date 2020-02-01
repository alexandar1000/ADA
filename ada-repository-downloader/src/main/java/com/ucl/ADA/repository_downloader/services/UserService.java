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

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void deleteUser(String name){
        List<User> list = listUsers();
        for(User u : list) {
            if(u.getUserName().equals(name)) userRepository.delete(u);
            return;
        }
    }

}
