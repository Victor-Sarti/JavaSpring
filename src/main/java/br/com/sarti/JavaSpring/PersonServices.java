package br.com.sarti.JavaSpring;

import br.com.sarti.JavaSpring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import java.util.concurrent.atomic.AtomicLong;

@Service //para poder chamar em outros arquivos
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public List<Person> findAll (){
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }


    public Person findById (String id){
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Victor");
        person.setLastName("Sarti");
        person.setAddress(" São Paulo - Brasil");
        person.setGender("Male");
        return person;


    }
    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname" + i);
        person.setLastName("Lastname" + i);
        person.setAddress(" Some address in Brasil");
        person.setGender("Male");
        return person;


    }

}
