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

    /**
     * List all users as User entities, including their Repositories, Branches, Snapshots and ClassNames.
     * @return a list of Users
     */
    public List<User> listUsers(){
        return (List<User>) userRepository.findAll();
    }

    /**
     * List only the usernames of each User in the database.
     * @return list of strings containing the usernames of each user
     */
    public List<String> listUserNames(){
        return userRepository.fetchUserNames();
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

    public User getUserByName(String name) {
        return userRepository.findByUserName(name);
    }
}
