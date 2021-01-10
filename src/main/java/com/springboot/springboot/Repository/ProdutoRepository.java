package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
