package br.com.sarti.JavaSpring.controllers;

import br.com.sarti.JavaSpring.PersonServices;
import br.com.sarti.JavaSpring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired // Anula o fato de usar o service = new personservices();
    private PersonServices service;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Person> findAll(){
        return service.findAll();

    }

    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public Person findById(@PathVariable("id") String id){
       return service.findById(id);

    }

}
