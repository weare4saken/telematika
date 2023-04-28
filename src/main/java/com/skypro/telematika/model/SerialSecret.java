package com.skypro.telematika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class SerialSecret {

    @Id
    private String serial;
    private String secret;
}
