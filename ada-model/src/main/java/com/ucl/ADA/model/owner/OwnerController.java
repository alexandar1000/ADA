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

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/{user_name}")
    public Owner getOwnerByName(@PathVariable String user_name) {
        return ownerService.getOwnerByName(user_name);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Owner> listAllUsers() {
        return ownerService.listOwners();
    }

    @CrossOrigin("http://localhost:4200")
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
