package com.ucl.ADA.model.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * List all owners as Owner  entities, including their Repositories, Branches, Snapshots and SourceFiles.
     * @return a list of Owners
     */
    public List<Owner> listOwners(){
        return ownerRepository.findAllByOrderByOwnerIDAsc();
    }

    /**
     * List only the usernames of each Owner in the database.
     * @return list of strings containing the usernames of each owner
     */
    public List<String> listUserNames(){
        return ownerRepository.fetchUserNames();
    }

    public Owner getOwner(Long id){
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner addOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public void deleteAllOwners(){
        ownerRepository.deleteAll();
    }

    public void deleteOwner(Long id){
        ownerRepository.deleteById(id);
    }

    public Owner getOwnerByName(String name) {
        return ownerRepository.findByUserName(name);
    }
}
