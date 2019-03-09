package com.marcskow.spring.bootstrap.java.person;

import com.marcskow.spring.bootstrap.java.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);
}
