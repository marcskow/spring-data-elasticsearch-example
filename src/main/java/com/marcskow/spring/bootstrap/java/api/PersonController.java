package com.marcskow.spring.bootstrap.java.api;

import com.marcskow.spring.bootstrap.java.common.Page;
import com.marcskow.spring.bootstrap.java.common.ResultPage;
import com.marcskow.spring.bootstrap.java.person.PersonService;
import com.marcskow.spring.bootstrap.java.person.model.Person;
import com.marcskow.spring.bootstrap.java.person.model.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResultPage<Person> getPersons(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size) {
        return personService.fetchPersons(new Page(page, size));
    }

    @PostMapping
    public void createPerson(@RequestBody PersonDto personDto) {
        personService.createPerson(personDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable  long id) {
        return ResponseEntity.ok(personService.retrievePersonById(id));
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable long id,
                             @RequestBody PersonDto personDto) {
        personService.updatePerson(id, personDto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
    }
}
