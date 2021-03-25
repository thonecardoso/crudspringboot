package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Pessoa;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("select p from Pessoa p where p.nome like %?1%")
    List<Pessoa> findPessoaByName(String nome);

    default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
        var pessoa = new Pessoa();
        pessoa.setNome(nome);

        var exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        var example = Example.of(pessoa, exampleMatcher);

        var pessoas = findAll(example, pageable);

        return pessoas;
    }
}
