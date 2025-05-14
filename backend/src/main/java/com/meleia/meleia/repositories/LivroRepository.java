package com.meleia.meleia.repositories;

import com.meleia.meleia.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
