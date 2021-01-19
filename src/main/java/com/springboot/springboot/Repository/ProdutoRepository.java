package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
