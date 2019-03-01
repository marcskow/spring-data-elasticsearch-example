package com.marcskow.spring.bootstrap.java.person;

class PersonNotFound extends RuntimeException {
    PersonNotFound(String message) {
        super(message);
    }
}
