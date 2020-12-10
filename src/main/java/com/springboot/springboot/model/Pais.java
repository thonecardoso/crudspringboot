package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

}
