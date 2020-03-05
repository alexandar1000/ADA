package com.ucl.ADA.model.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = "/{owner}")
    public Owner getOwnerByName(@PathVariable String owner) {
        return ownerService.getOwnerByName(owner);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Owner> listAllUsers() {
        return ownerService.listOwners();
    }

    @GetMapping("/names")
    public List<String> listAllUserNames() {
        return ownerService.listUserNames();
    }

    @DeleteMapping
    public void deleteAll() {
        ownerService.deleteAllOwners();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOwnerById(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

}
