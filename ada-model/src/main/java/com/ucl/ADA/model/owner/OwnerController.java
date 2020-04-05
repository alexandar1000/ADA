package com.ucl.ADA.model.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * Endpoint for getting an owner corresponding to name
     * @param owner username of the owner
     * @return retrieved owner
     */
    @PostMapping(value = "/{owner}")
    public Owner getOwnerByName(@PathVariable String owner) {
        return ownerService.getOwnerByName(owner);
    }

    /**
     * Endpoint for getting all owners in the database
     * @return list of all owners
     */
    @PostMapping
    public List<Owner> listAllUsers() {
        return ownerService.listOwners();
    }

    /**
     * Endpoint for getting all usernames of all owners of Git repositories in the database
     * @return list of all usernames
     */
    @PostMapping("/names")
    public List<String> listAllUsername() {
        return ownerService.listAllUsername();
    }

    /**
     * Endpoint for deleting all owners in the database
     */
    @DeleteMapping
    public void deleteAll() {
        ownerService.deleteAllOwners();
    }

    /**
     * Endpoint for deleting a owner given an ID
     * @param id id of a particular owner
     */
    @DeleteMapping(value = "/{id}")
    public void deleteOwnerById(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

}
