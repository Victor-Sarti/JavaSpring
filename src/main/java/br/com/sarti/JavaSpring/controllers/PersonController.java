package br.com.sarti.JavaSpring.controllers;

import br.com.sarti.JavaSpring.data.dto.v1.PersonDTO;
import br.com.sarti.JavaSpring.data.dto.v2.PersonDTOV2;
import br.com.sarti.JavaSpring.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired // Anula o fato de usar o service = new personservices();
    private PersonServices service;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)

    public List<PersonDTO> findAll() {
        return service.findAll();

    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public PersonDTO findById(@PathVariable("id") Long id) {
        var person = service.findById(id);
        person.setBirthDay( new Date());
        //person.setPhoneNumber("+55 (11) 96599-6587");
        person.setPhoneNumber("");
        person.setLastName(null);
        person.setSensitiveData("Foor Bar");

        return person;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);

    }

    @PostMapping(value = "/api/person/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
        return service.createV2(person);

    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  //retorna o erro 204
    }
}

