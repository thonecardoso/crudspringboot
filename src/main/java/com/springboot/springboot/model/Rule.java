package com.springboot.springboot.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Rule implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRule;

    @Override
    public String getAuthority() { //ROLE_ADMIN, ROLE_GERENTE , ...
        return this.nomeRule;
    }
}
