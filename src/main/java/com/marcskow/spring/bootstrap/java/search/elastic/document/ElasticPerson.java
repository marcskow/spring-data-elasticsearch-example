package com.marcskow.spring.bootstrap.java.search.elastic.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.Id;

@Document(indexName = "elastic", type = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Setting(settingPath = "/settings/settings.json")
@Mapping(mappingPath = "/mappings/mappings.json")
public class ElasticPerson {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private long phoneNumber;
}
