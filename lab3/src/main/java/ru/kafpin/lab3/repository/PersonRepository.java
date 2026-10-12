package ru.kafpin.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kafpin.lab3.entity.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByLastNameContainsIgnoreCase(String lastName);
    List<Person> findByAddressCityContainsIgnoreCase(String city);
    List<Person> findByNationalityNameContainsIgnoreCase(String nationality);
}