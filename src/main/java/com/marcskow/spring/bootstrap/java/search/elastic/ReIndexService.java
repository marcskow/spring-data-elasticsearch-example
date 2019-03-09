package com.marcskow.spring.bootstrap.java.search.elastic;

import com.marcskow.spring.bootstrap.java.person.PersonRepository;
import com.marcskow.spring.bootstrap.java.person.model.Person;
import com.marcskow.spring.bootstrap.java.search.elastic.document.ElasticPerson;
import com.marcskow.spring.bootstrap.java.search.elastic.repository.ElasticPersonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReIndexService {

    private ElasticPersonRepository elasticPersonRepository;

    private PersonRepository personRepository;

    public ReIndexService(ElasticPersonRepository elasticPersonRepository, PersonRepository personRepository) {
        this.elasticPersonRepository = elasticPersonRepository;
        this.personRepository = personRepository;
    }

    public void reindexPersonRepository() {
        elasticPersonRepository.deleteAll();
        personRepository.findAll().forEach(person -> {
            if (elasticPersonRepository.findAllByFirstNameAndLastNameAndAge(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAge(),
                    PageRequest.of(0, 1)
            ).getTotalElements() == 0) {
                indexNewPerson(person);
            }
        });
    }

    private void indexNewPerson(Person person) {
        ElasticPerson elasticPerson = new ElasticPerson();
        elasticPerson.setFirstName(person.getFirstName());
        elasticPerson.setLastName(person.getLastName());
        elasticPerson.setPhoneNumber(person.getPhoneNumber());
        elasticPerson.setAge(person.getAge());
        elasticPersonRepository.save(elasticPerson);
    }

}
