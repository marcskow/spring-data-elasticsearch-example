package com.marcskow.spring.bootstrap.java.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigProperty {

    @Id
    @Column(name = "config_property_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String key;

    private String value;
}
