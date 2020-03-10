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
        return ownerRepository.findAllByOrderByIdAsc();
    }

    /**
     * List only the usernames of each Owner in the database.
     * @return list of strings containing the usernames of each owner
     */
    public List<String> listAllUsername(){
        return ownerRepository.fetchAllUsername();
    }

    /**
     * Get Owner entity by ID
     * @param id id of the owner in the database
     * @return Owner entity corresponding to id
     */
    public Owner getOwner(Long id){
        return ownerRepository.findById(id).orElse(null);
    }

    /**
     * Add owner to database
     * @param owner to be added
     * @return added Owner entity
     */
    public Owner addOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    /**
     * Delete all Owner entities un the database
     */
    public void deleteAllOwners(){
        ownerRepository.deleteAll();
    }

    /**
     * Delete Owner by ID
     * @param id of the corresponding Owner entity
     */
    public void deleteOwner(Long id){
        ownerRepository.deleteById(id);
    }

    /**
     * Get owner by username
     * @param name Git username of the Owner
     * @return retrieved Owner
     */
    public Owner getOwnerByName(String name) {
        return ownerRepository.findByUsername(name);
    }
}
