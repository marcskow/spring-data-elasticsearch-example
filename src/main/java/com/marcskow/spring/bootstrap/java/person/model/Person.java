package com.marcskow.spring.bootstrap.java.person.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private int age;

    private long phoneNumber;
    
    public void updateBy(PersonDto personDto) {
        firstName = personDto.getFirstName();
        lastName = personDto.getLastName();
        age = personDto.getAge();
        phoneNumber = personDto.getPhoneNumber();
    }
}