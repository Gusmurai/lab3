package ru.kafpin.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kafpin.lab3.entity.Nationality;

public interface NationalityRepository extends CrudRepository<Nationality, Long> {

}