package br.com.sarti.JavaSpring.services;

import br.com.sarti.JavaSpring.controllers.PersonController;
import br.com.sarti.JavaSpring.data.dto.v1.PersonDTO;
import br.com.sarti.JavaSpring.data.dto.v2.PersonDTOV2;
import br.com.sarti.JavaSpring.exeception.RequireObjectIsNullException;
import br.com.sarti.JavaSpring.exeception.ResouceNotFoundException;
import static br.com.sarti.JavaSpring.mapper.ObjectMapper.parseListObjects;
import static br.com.sarti.JavaSpring.mapper.ObjectMapper.parseObject;

import br.com.sarti.JavaSpring.mapper.custom.PersonMapper;
import br.com.sarti.JavaSpring.model.Person;
import br.com.sarti.JavaSpring.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service //para poder chamar em outros arquivos
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


    public List<PersonDTO> findAll (){
        logger.info("Finding all people!");
        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
       persons.forEach(this::addHateoasLinks);
        return persons;

    }

    public PersonDTO findById (Long id){
        logger.info("Finding one Person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }


    public PersonDTO create (PersonDTO person) {

       if(person == null) throw new RequireObjectIsNullException();
        logger.info("Creating one person!");

      var entity =  parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }

    public PersonDTOV2 createV2 (PersonDTOV2 person) {
        logger.info("Creating one person!");
      var entity =  converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save (entity));
    }

     public PersonDTO update ( PersonDTO person) {
         if(person == null) throw new RequireObjectIsNullException();

         logger.info("Updating one person!");
         Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

         entity.setFirstName(person.getFirstName());
         entity.setLastName(person.getLastName());
         entity.setAddress(person.getAddress());
         entity.setGender(person.getGender());
         var dto = parseObject(repository.save(entity), PersonDTO.class);
         addHateoasLinks(dto);
         return dto;


     }

    public void delete(Long id){
        logger.info("Deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id){
        logger.info("Disabling one person!");
        repository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

        repository.disablePerson(id);
        var entity = repository.findById(id).get();
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    private  void addHateoasLinks (PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("UPDATE"));

        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));


        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));


    }


}
