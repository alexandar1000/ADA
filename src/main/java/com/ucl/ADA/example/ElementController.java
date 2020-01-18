package com.ucl.ADA.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * This is a controller and it should preferably delegate the requests from the calls to the services in the Service
 * class. Preferably, it shouldn't contain a lot of functionality.
 * */

@Controller
@RequestMapping("/example")
public class ElementController {

    @Autowired ElementServices elementServices;

    @PutMapping("/element")
    @ResponseBody
    public void sayWisdom(@RequestParam(name="name", required=false, defaultValue="Alex") String firstName) {
        elementServices.saveElement(firstName);
    }

    @GetMapping("/allElements")
    @ResponseBody
    public List<Element> sayAllWisdom() {
        return elementServices.list();
    }

}
