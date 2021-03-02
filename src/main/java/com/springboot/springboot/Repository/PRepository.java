package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PRepository extends JpaRepository<Pessoa, Long> {
}
