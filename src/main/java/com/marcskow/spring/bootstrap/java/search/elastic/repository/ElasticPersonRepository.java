package com.marcskow.spring.bootstrap.java.search.elastic.repository;

import com.marcskow.spring.bootstrap.java.search.elastic.document.ElasticPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticPersonRepository extends ElasticsearchRepository<ElasticPerson, String> {
    Page<ElasticPerson> findByFirstName(String firstName, Pageable pageable);

    Page<ElasticPerson> findAllByFirstNameAndLastNameAndAge(String firstName, String lastName, int age, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"persons.firstName\": \"?0\"}}]}}")
    Page<ElasticPerson> findByFirstNameUsingCustomQuery(String firstName, Pageable pageable);
}
