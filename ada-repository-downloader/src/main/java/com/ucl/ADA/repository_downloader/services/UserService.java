package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for listing and deleting users in the database.
 */

@Service
public class UserService {

    @Autowired private OwnerRepository ownerRepository;

    /**
     * List all users as User entities, including their Repositories, Branches, Snapshots and ClassNames.
     * @return a list of Users
     */
    public List<Owner> listUsers(){
        return (List<Owner>) ownerRepository.findAll();
    }

    /**
     * List only the usernames of each User in the database.
     * @return list of strings containing the usernames of each user
     */
    public List<String> listUserNames(){
        return ownerRepository.fetchUserNames();
    }

    public Owner getUser(Long id){
        return ownerRepository.findById(id).orElse(null);
    }

    public void addUser(Owner owner){
        ownerRepository.save(owner);
    }

    public void deleteAllUsers(){
        ownerRepository.deleteAll();
    }

    public void deleteUser(Long id){
        ownerRepository.deleteById(id);
    }

    public Owner getUserByName(String name) {
        return ownerRepository.findByUserName(name);
    }
}
