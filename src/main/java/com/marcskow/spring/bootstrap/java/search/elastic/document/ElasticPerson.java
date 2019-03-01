package com.marcskow.spring.bootstrap.java.search.elastic.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "elastic", type = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElasticPerson {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private long phoneNumber;
}