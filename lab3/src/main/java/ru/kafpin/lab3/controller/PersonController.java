package ru.kafpin.lab3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kafpin.lab3.dto.PersonDto;
import ru.kafpin.lab3.entity.Person;
import ru.kafpin.lab3.repository.AddressRepository;
import ru.kafpin.lab3.repository.NationalityRepository;
import ru.kafpin.lab3.repository.PersonRepository;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private NationalityRepository nationalityRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "main";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model) {
        model.addAttribute("personDto", new PersonDto()); // Передаем пустой DTO
        model.addAttribute("nationalities", nationalityRepository.findAll());
        model.addAttribute("addresses", addressRepository.findAll());
        return "add_person";
    }

    @PostMapping("/new")
    public String createPerson(@ModelAttribute("personDto") @Valid PersonDto dto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nationalities", nationalityRepository.findAll());
            model.addAttribute("addresses", addressRepository.findAll());
            return "add_person";
        }

        // Если ID есть - ищем в базе (обновление), если нет - создаем нового
        Person person = (dto.getId() != null) ?
                personRepository.findById(dto.getId()).orElse(new Person()) :
                new Person();

        person.setLastName(dto.getLastName());
        person.setFirstName(dto.getFirstName());
        person.setMiddleName(dto.getMiddleName());
        person.setGender(dto.getGender());
        person.setHeight(dto.getHeight());
        person.setWeight(dto.getWeight());
        person.setBirthDate(dto.getBirthDate());
        person.setPhoneNumber(dto.getPhoneNumber());

        person.setNationality(nationalityRepository.findById(dto.getNationalityId()).orElse(null));
        person.setAddress(addressRepository.findById(dto.getAddressId()).orElse(null));

        personRepository.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/update/{id}")
    public String editPersonForm(@PathVariable("id") Long id, Model model) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return "redirect:/persons";
        }

        // данные из БД в DTO для формы
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setLastName(person.getLastName());
        dto.setFirstName(person.getFirstName());
        dto.setMiddleName(person.getMiddleName());
        dto.setGender(person.getGender());
        dto.setHeight(person.getHeight());
        dto.setWeight(person.getWeight());
        dto.setBirthDate(person.getBirthDate());
        dto.setPhoneNumber(person.getPhoneNumber());
        dto.setNationalityId(person.getNationality().getId());
        dto.setAddressId(person.getAddress().getId());

        model.addAttribute("personDto", dto);
        model.addAttribute("nationalities", nationalityRepository.findAll());
        model.addAttribute("addresses", addressRepository.findAll());
        return "add_person";
    }

    @PostMapping("/search")
    public String search(@RequestParam String criteria, @RequestParam String query, Model model) {
        Iterable<Person> results = null;
        if (criteria.equals("lastName")) {
            results = personRepository.findByLastNameContainsIgnoreCase(query);
        } else if (criteria.equals("city")) {
            results = personRepository.findByAddressCityContainsIgnoreCase(query);
        } else if (criteria.equals("nationality")) {
            results = personRepository.findByNationalityNameContainsIgnoreCase(query);
        }
        model.addAttribute("persons", results);
        return "main";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
        return "redirect:/persons";
    }
}