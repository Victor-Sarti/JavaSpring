package br.com.sarti.JavaSpring.services;

import br.com.sarti.JavaSpring.data.dto.v1.PersonDTO;
import br.com.sarti.JavaSpring.data.dto.v2.PersonDTOV2;
import br.com.sarti.JavaSpring.exeception.ResouceNotFoundException;
import static br.com.sarti.JavaSpring.mapper.ObjectMapper.parseListObjects;
import static br.com.sarti.JavaSpring.mapper.ObjectMapper.parseObject;

import br.com.sarti.JavaSpring.mapper.custom.PersonMapper;
import br.com.sarti.JavaSpring.model.Person;
import br.com.sarti.JavaSpring.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById (Long id){
        logger.info("Finding one Person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);

    }


    public PersonDTO create (PersonDTO person) {
        logger.info("Creating one person!");
      var entity =  parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2 (PersonDTOV2 person) {
        logger.info("Creating one person!");
      var entity =  converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save (entity));
    }

     public PersonDTO update ( PersonDTO person) {
        logger.info("Updating one person!");
         Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

         entity.setFirstName(person.getFirstName());
         entity.setLastName(person.getLastName());
         entity.setAddress(person.getAddress());
         entity.setGender(person.getGender());
         return parseObject(repository.save(entity), PersonDTO.class);

     }

    public void delete(Long id){
        logger.info("Deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
