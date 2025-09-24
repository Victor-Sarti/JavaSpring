package br.com.sarti.JavaSpring.services;

import br.com.sarti.JavaSpring.exeception.ResouceNotFoundException;
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

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


    public List<Person> findAll (){
        logger.info("Finding all people!");
        return repository.findAll();
    }

    public Person findById (Long id){
        logger.info("Finding one Person!");

        return repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));


    }


    public Person create ( Person person) {
        logger.info("Creating one person!");

        return repository.save(person);
    }

     public Person update ( Person person) {
        logger.info("Updating one person!");
         Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

         entity.setFirstName(person.getFirstName());
         entity.setLastName(person.getLastName());
         entity.setAddress(person.getAddress());
         entity.setGender(person.getGender());
         return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResouceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
