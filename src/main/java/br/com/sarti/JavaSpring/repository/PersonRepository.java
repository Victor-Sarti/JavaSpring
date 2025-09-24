package br.com.sarti.JavaSpring.repository;

import br.com.sarti.JavaSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
