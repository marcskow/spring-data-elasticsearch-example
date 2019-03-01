package com.marcskow.spring.bootstrap.java.person.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    public static PersonDto fromPerson(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAge(),
                person.getPhoneNumber()
        );
    }

    private long id;

    private String firstName;

    private String lastName;

    private int age;

    private long phoneNumber;
}