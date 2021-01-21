package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Pessoa;
import com.springboot.springboot.model.Telefone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
    @Query(
            value = "SELECT pessoa_id FROM public.telefone WHERE id=?",
            nativeQuery = true)
    Long findIdPessoa(@Param("id") Long l);


}
