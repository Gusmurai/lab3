package ru.kafpin.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kafpin.lab3.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}