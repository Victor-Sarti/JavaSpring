package br.com.sarti.JavaSpring.controllers;

import br.com.sarti.JavaSpring.services.PersonServices;
import br.com.sarti.JavaSpring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired // Anula o fato de usar o service = new personservices();
    private PersonServices service;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Person> findAll() {
        return service.findAll();

    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public Person create(@RequestBody Person person) {
        return service.create(person);

    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public Person update(@RequestBody Person person) {
        return service.update(person);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  //retorna o erro 204
    }
}

