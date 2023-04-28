package com.skypro.telematika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Indication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long value;
    private Instant createdDate = Instant.now();
    @ManyToOne
    private SerialSecret serialSecret;

}
