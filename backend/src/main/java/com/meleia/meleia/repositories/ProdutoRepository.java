package com.meleia.meleia.repositories;

import com.meleia.meleia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}


